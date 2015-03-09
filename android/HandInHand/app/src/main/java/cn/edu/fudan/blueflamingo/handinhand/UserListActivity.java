package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.adapter.UserAdapter;
import cn.edu.fudan.blueflamingo.handinhand.middleware.FavoriteHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.UserHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.User;
import cn.edu.fudan.blueflamingo.handinhand.view.SwipeRefreshAndLoadLayout;


/**
 * The type User list activity.
 */
public class UserListActivity extends ActionBarActivity {

    /**
     * The constant MODE.
     */
    public static int MODE = 0;
    /**
     * The constant I_FAVORITE.
     */
    public static final int I_FAVORITE = 0;
    /**
     * The constant FAVORITE_ME.
     */
    public static final int FAVORITE_ME = 1;

	private List<User> users = new ArrayList<>();
	private int uid;
	private SwipeRefreshAndLoadLayout mSwipeLayout;
	private RecyclerView recyclerView;
	private UserAdapter userAdapter;
	private UserHelper userHelper = new UserHelper();
	private FavoriteHelper favoriteHelper = new FavoriteHelper();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
		MODE = getIntent().getExtras().getInt("MODE");
		uid = getIntent().getExtras().getInt("uid");
		users.add(new User("testUsername","Loading...","Please wait..."));
		initToolbar();
		initItem();
		initLoadAndRefresh();
		(new LoadUserTask()).execute();
	}

	private void initLoadAndRefresh() {
		mSwipeLayout = (SwipeRefreshAndLoadLayout) findViewById(R.id.user_item_container);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshAndLoadLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				(new LoadUserTask()).execute();
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
		mSwipeLayout.setmMode(SwipeRefreshAndLoadLayout.Mode.PULL_FROM_START);
	}

	private void initToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.user_list_toolbar);
		if (toolbar != null) {
			if (MODE == I_FAVORITE) {
				toolbar.setTitle("我关注的人");
			} else {
				toolbar.setTitle("关注我的人");
			}
			toolbar.inflateMenu(R.menu.menu_user_list);
			setSupportActionBar(toolbar);
		}
	}

	private void initItem() {
		recyclerView = (RecyclerView) findViewById(R.id.user_item_recyclerview);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setHasFixedSize(true);
		userAdapter = new UserAdapter(this, users);
		userAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent uItemIntent = new Intent(UserListActivity.this, UserInfoActivity.class);
				uItemIntent.putExtra("uid", users.get(position).getId());
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
		int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}

	private class LoadUserTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected void onPreExecute() {
			mSwipeLayout.setRefreshing(true);
		}

		@Override
		protected Integer doInBackground(Integer... params) {
			users.clear();
			if (MODE == I_FAVORITE) {
				users.addAll(favoriteHelper.listUsers(uid));
			} else {
				users.addAll(userHelper.listFollowers(uid));
			}
			if (users.size() > 0) {
				return 1;
			} else {
				return 0;
			}
		}

		@Override
		protected void onPostExecute(Integer res) {
			if (res == 1) {
				userAdapter.notifyDataSetChanged();
			}
			mSwipeLayout.setRefreshing(false);
		}

	}
}
