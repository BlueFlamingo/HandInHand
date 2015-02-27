package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.handinhand.lib.AppUtility;
import cn.edu.fudan.blueflamingo.handinhand.middleware.UserHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.User;


public class MainActivity extends ActionBarActivity {

	public Global globalVal;

	private final static int AUTH_ACTIVITY = 0;
	private final static int EDIT_ACTIVITY = 1;

	private TextView nicknameTextView;
	private ImageView portraitImageView;

	private ViewPager mViewPager;
	private MainFragmentPagerAdapter mMainFragmentPagerAdapter;
	private ImageView cursor;
	private int offset;
	private int currentTabID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		globalVal = (Global) getApplication();
		initToolBar();
		initCursor();
		bindTabClick();
		initViewPager();
		AppUtility.openDiskLruCache(this, 1024 * 1024 * 10);
		if (!globalVal.getLoginFlag()) {
			Intent authIntent = new Intent(this, AuthActivity.class);
			startActivity(authIntent);
		} else {
			nicknameTextView.setText(globalVal.getNickname());
		}
	}

	private void initViewPager() {
		mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
		mMainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mMainFragmentPagerAdapter);
		mViewPager.setCurrentItem(0);
		currentTabID = 0;
		mViewPager.setOnPageChangeListener(new MainOnPageChangeListener());
	}

	private void bindTabClick() {
		TextView tab1 = (TextView) findViewById(R.id.main_tab_1);
		TextView tab2 = (TextView) findViewById(R.id.main_tab_2);
		tab1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(0);
			}
		});
		tab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(1);
			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		if (globalVal.getLoginFlag()) {
			(new LoadProfile()).execute();
		}
	}

	private void initCursor() {
		cursor = (ImageView) findViewById(R.id.main_tab_cursor);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int cursorWidth = displayMetrics.widthPixels / 2;
		cursor.getLayoutParams().width = cursorWidth;
		offset = displayMetrics.widthPixels - cursorWidth;
	}

	private void initToolBar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
		if (toolbar != null) {
			//set toolbar features
			toolbar.setTitle("Hand in Hand");
			toolbar.setSubtitle("Welcome!");
			toolbar.inflateMenu(R.menu.menu_main);
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			//initialize drawer
			DrawerLayout mDrawerLayout;
			ActionBarDrawerToggle mDrawerToggle;
			mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
			mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
			mDrawerToggle.syncState();
			mDrawerLayout.setDrawerListener(mDrawerToggle);

			//bind
			nicknameTextView = (TextView) findViewById(R.id.drawer_username);
			portraitImageView = (ImageView) findViewById(R.id.drawer_head);

			//initial btn_login
			//check whether the login process is needed and change the text of it
			final com.gc.materialdesign.views.ButtonFlat btnLogin = (com.gc.materialdesign.views.ButtonFlat) findViewById(R.id.btn_login);
			if (globalVal.getLoginFlag()) {
				btnLogin.setText("登出");
				btnLogin.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						btnLogin.setText("登出");
						Intent loginIntent = new Intent(MainActivity.this, AuthActivity.class);
						startActivityForResult(loginIntent, AUTH_ACTIVITY);
					}
				});
			} else {
				nicknameTextView.setText("随便看看的人");
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id) {
			case R.id.action_edit:
				if (globalVal.getLoginFlag()) {
					//jump to QuestionEditActivity
					Intent editIntent = new Intent(this, QuestionEditActivity.class);
					editIntent.putExtra("mode", 1);
					startActivity(editIntent);
				} else {
					Toast.makeText(this, "请先登录后再发布问题", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.action_search:
				Intent searchIntent = new Intent(this, SearchActivity.class);
				startActivity(searchIntent);
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	//handle drawer button onClick
	public void BtnLoginOnclick(View view) {
		ButtonFlat btnLogin = (ButtonFlat) findViewById(R.id.btn_login);
		if (btnLogin.getText().equals("登出")) {
			globalVal.setLoginFlag(false);
		}
		Intent loginIntent = new Intent(this, AuthActivity.class);
		startActivityForResult(loginIntent, AUTH_ACTIVITY);
	}

	@Override
	protected void onActivityResult(int reqCode, int resCode, Intent data) {
		super.onActivityResult(reqCode, resCode, data);
		switch (reqCode) {
			case AUTH_ACTIVITY:
				com.gc.materialdesign.views.ButtonFlat btnLogin = (com.gc.materialdesign.views.ButtonFlat) findViewById(R.id.btn_login);
				if (globalVal.getLoginFlag()) {
					btnLogin.setText("登出");
				} else {
					btnLogin.setText("登陆");
				}
				switch (resCode) {
					case AuthActivity.HANG_OUT:
						globalVal.setLoginFlag(false);
						nicknameTextView.setText("随便逛逛的人");
						portraitImageView.setImageResource(R.drawable.app);
						break;
					case AuthActivity.REGISTERED:
					case AuthActivity.LOGINED:
						(new LoadProfile()).execute();
				}
				break;
			default:
				break;
		}
	}

	@Override
	public void onBackPressed() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		if (fragmentManager.getBackStackEntryCount() != 0) {
			fragmentManager.popBackStack();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		com.gc.materialdesign.views.ButtonFlat btnLogin = (com.gc.materialdesign.views.ButtonFlat) findViewById(R.id.btn_login);
		if (globalVal.getLoginFlag()) {
			btnLogin.setText("登出");
		} else {
			btnLogin.setText("登陆");
		}
	}

	private class LoadProfile extends AsyncTask<Integer, Integer, Integer> {

		private UserHelper userHelper = new UserHelper();
		private User u;
		private Bitmap bitmap;

		@Override
		protected Integer doInBackground(Integer... params) {
			u = userHelper.getByUid(globalVal.getUid());
			globalVal.setNickname(u.getNickname());
			bitmap = AppUtility.getImage(u.getPortrait());
			return 0;
		}

		@Override
		protected void onPostExecute(Integer res) {
			nicknameTextView.setText(u.getNickname());
			portraitImageView.setImageBitmap(bitmap);
		}

	}

	private class MainFragmentPagerAdapter extends FragmentPagerAdapter {

		private Fragment blankFragment;
		private Fragment mFragmentAtPos0;
		private FragmentManager fragmentManager;
		private final static int ITEM_NUM = 2;

		public MainFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			fragmentManager = fm;
			blankFragment = new BlankFragment();
		}

		@Override
		public int getCount() {
			return ITEM_NUM;
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				if (mFragmentAtPos0 == null) {
					QuestionListFragment q = QuestionListFragment.newInstance(0);
					q.setOnListEmptyListener(new QuestionListFragment.OnListEmptyListener() {
						@Override
						public void onListEmpty() {
							fragmentManager.beginTransaction()
									.remove(mFragmentAtPos0)
									.commit();
							mFragmentAtPos0 = blankFragment;
							notifyDataSetChanged();
						}
					});
					mFragmentAtPos0 = q;
				}
				return mFragmentAtPos0;
			} else {
				return HomePageFragment.newInstance();
			}
		}

		@Override
		public int getItemPosition(Object object) {
			if (object instanceof QuestionListFragment && mFragmentAtPos0 instanceof BlankFragment) {
				return POSITION_NONE;
			}
			return POSITION_UNCHANGED;
		}

	}

	private class MainOnPageChangeListener implements ViewPager.OnPageChangeListener {

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
			switch (arg0) {
				case 0:
					if (currentTabID == 1) {
						toolbar.setTitle("30分钟紧急问答");
						animation = new TranslateAnimation(offset, 0, 0, 0);
						//Log.d(AppUtility.APPNAME, "swipe to 0");
					}
					break;
				case 1:
					if (currentTabID == 0) {
						toolbar.setTitle("首页");
						animation = new TranslateAnimation(0, offset, 0, 0);
						//Log.d(AppUtility.APPNAME, "swipe to 1");
					}
					break;
			}
			currentTabID = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

	}
}
