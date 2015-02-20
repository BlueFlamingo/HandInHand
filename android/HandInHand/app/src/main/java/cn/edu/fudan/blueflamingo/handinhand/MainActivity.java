package cn.edu.fudan.blueflamingo.handinhand;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import cn.edu.fudan.blueflamingo.handinhand.lib.AppUtility;
import cn.edu.fudan.blueflamingo.handinhand.middleware.UserHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.User;


public class MainActivity extends ActionBarActivity {

	public Global globalVal;

	private final static int AUTH_ACTIVITY = 0;

	private TextView nicknameTextView;
	private ImageView portraitImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		globalVal = (Global) getApplication();
		initToolBar();
		initHomePageFragment();
		AppUtility.openDiskLruCache(this, 1024 * 1024 * 10);
		if (!globalVal.getLoginFlag()) {
			Intent authIntent = new Intent(this, AuthActivity.class);
			startActivity(authIntent);
		} else {
			nicknameTextView.setText(globalVal.getNickname());
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		(new LoadProfile()).execute();
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

	private void initHomePageFragment() {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.main_fragment, new HomePageFragment())
				.commit();
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
		FragmentManager fragmentManager = getFragmentManager();
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

		@Override
		protected Integer doInBackground(Integer... params) {
			u = userHelper.getByUid(globalVal.getUid());
			globalVal.setNickname(u.getNickname());
			return 0;
		}

		@Override
		protected void onPostExecute(Integer res) {
			nicknameTextView.setText(u.getNickname());
			portraitImageView.setImageBitmap(AppUtility.getImage(u.getPortrait()));
		}

	}
}
