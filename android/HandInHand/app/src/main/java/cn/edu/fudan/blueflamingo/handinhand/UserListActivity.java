package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.adapter.UserAdapter;
import cn.edu.fudan.blueflamingo.handinhand.model.User;
import cn.edu.fudan.blueflamingo.handinhand.view.SwipeRefreshAndLoadLayout;


public class UserListActivity extends ActionBarActivity {

	public static int MODE = 0;
	public static final int I_FAVORITE = 0;
	public static final int FAVORITE_ME = 1;

	private List<User> users = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
		MODE = getIntent().getExtras().getInt("MODE");
		users.add(new User("testUsername","testNickname","12345"));
		initToolbar();
		initItem();
		initLoadAndRefresh();
	}

	private void initLoadAndRefresh() {
		final SwipeRefreshAndLoadLayout mSwipeLayout;
		mSwipeLayout = (SwipeRefreshAndLoadLayout) findViewById(R.id.user_item_container);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshAndLoadLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						mSwipeLayout.setRefreshing(false);
						//refresh
					}
				}, 1000);
			}

			@Override
			public void onLoadMore() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						mSwipeLayout.setRefreshing(false);
						//load more
					}
				}, 1000);
			}
		});
		mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		mSwipeLayout.setmMode(SwipeRefreshAndLoadLayout.Mode.BOTH);
	}

	private void initToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.user_list_toolbar);
		if (toolbar != null) {
			if (MODE == I_FAVORITE) {
				toolbar.setTitle("关注的人");
			} else {
				toolbar.setTitle("已关注的人");
			}
			toolbar.inflateMenu(R.menu.menu_user_list);
			setSupportActionBar(toolbar);
		}
	}

	private void initItem() {
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.user_item_recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setHasFixedSize(true);
		UserAdapter userAdapter = new UserAdapter(this, users);
		userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent uItemIntent = new Intent(UserListActivity.this, UserInfoActivity.class);
				startActivity(uItemIntent);
			}
		});
		recyclerView.setAdapter(userAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_user_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
