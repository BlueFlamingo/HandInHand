package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonFlat;

import cn.edu.fudan.blueflamingo.handinhand.lib.AppUtility;
import cn.edu.fudan.blueflamingo.handinhand.middleware.FavoriteHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.UserHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.User;


/**
 * The type User info activity.
 */
public class UserInfoActivity extends ActionBarActivity {

	private Global global;
	private int uid;
	private UserHelper userHelper = new UserHelper();
	private FavoriteHelper favoriteHelper = new FavoriteHelper();
	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		initToolbar();
		global = (Global) getApplication();
		uid = getIntent().getExtras().getInt("uid");
		RelativeLayout userAsked = (RelativeLayout) findViewById(R.id.user_info_q_container);
		userAsked.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent qListIntent = new Intent(UserInfoActivity.this, QuestionListActivity.class);
				qListIntent.putExtra("TOPIC", "问过的问题");
				qListIntent.putExtra("TID", -uid);
				startActivity(qListIntent);
			}
		});

		RelativeLayout userAnswered = (RelativeLayout) findViewById(R.id.user_info_a_container);
		userAnswered.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent aListIntent = new Intent(UserInfoActivity.this, QuestionItemActivity.class);
				aListIntent.putExtra("MODE", QuestionItemActivity.FROM_USER_ASKED_LIST);
				startActivity(aListIntent);
			}
		});

        RelativeLayout userFavQuestion = (RelativeLayout) findViewById(R.id.user_info_fav_q_container);
        userFavQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent favQListIntent = new Intent(UserInfoActivity.this, QuestionListActivity.class);
                favQListIntent.putExtra("TOPIC", global.getNickname() + "关注的问题");
                favQListIntent.putExtra("TID", AppUtility.FAV_QUESTION_LIST);
                startActivity(favQListIntent);
            }
        });

		initIFav();
		initFavdMe();

		(new LoadTask()).execute();

		//bind favorite
		ButtonFlat btn_favorite = (ButtonFlat) findViewById(R.id.user_info_btn_favorite);
		if (uid == global.getUid()) {
			btn_favorite.setEnabled(false);
			btn_favorite.setVisibility(View.GONE);
		} else {
			(new FavoriteTask()).execute(true);
			btn_favorite.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					(new FavoriteTask()).execute(false);
				}
			});
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		(new LoadProfileTask()).execute();
	}

	private void initIFav() {
		final RelativeLayout iFav = (RelativeLayout) findViewById(R.id.user_info_i_fav_container);
		iFav.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent iFavIntent = new Intent(UserInfoActivity.this, UserListActivity.class);
				iFavIntent.putExtra("MODE", UserListActivity.I_FAVORITE);
				iFavIntent.putExtra("uid", uid);
				startActivity(iFavIntent);
			}
		});
	}

	private void initFavdMe() {
		final RelativeLayout favMe = (RelativeLayout) findViewById(R.id.user_info_fav_me_container);
		favMe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent favMeIntent = new Intent(UserInfoActivity.this, UserListActivity.class);
				favMeIntent.putExtra("MODE", UserListActivity.FAVORITE_ME);
				favMeIntent.putExtra("uid", uid);
				startActivity(favMeIntent);
			}
		});
	}

	private void initToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.user_info_toolbar);
		if (toolbar != null) {
			toolbar.setTitle("个人信息");
			toolbar.inflateMenu(R.menu.menu_user_info);
			setSupportActionBar(toolbar);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_user_info, menu);
		MenuItem editItem = menu.findItem(R.id.action_edit);
		//set menu
		if (uid != global.getUid()) {
			editItem.setEnabled(false);
			editItem.setVisible(false);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		switch (id) {
			case R.id.action_edit:
				Intent userEditIntent = new Intent(this, UserEditActivity.class);
				userEditIntent.putExtra("uid", uid);
				startActivity(userEditIntent);
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	//input TRUE->check current favorite status
	//		FALSE->switch favorite status
	private class FavoriteTask extends AsyncTask<Boolean, Integer, Integer> {

		@Override
		protected Integer doInBackground(Boolean... params) {
			if (params[0]) {
				return favoriteHelper.isUserFavorite(global.getUid(), uid);
			} else {
				return favoriteHelper.switchUserFavorite(global.getUid(), uid);
			}
		}

		@Override
		protected void onPostExecute(Integer res) {
			ButtonFlat btn_favorite = (ButtonFlat) findViewById(R.id.user_info_btn_favorite);
			switch (res) {
				case 0:
					btn_favorite.setText("关注");
					btn_favorite.setBackgroundColor(getResources().getColor(R.color.yellow700));
					break;
				case 1:
					btn_favorite.setText("取消关注");
					btn_favorite.setBackgroundColor(getResources().getColor(R.color.yellow900));
					break;
			}
		}
	}

	private class LoadTask extends AsyncTask<Integer, Integer, Integer> {

		private User u;
		private int followNum;
		private int followerNum;
		private int qNum;
		private int aNum;

		@Override
		protected Integer doInBackground(Integer... params) {
			u = userHelper.getByUid(uid);
			followNum = userHelper.countFollowUsers(uid);
			followerNum = userHelper.countFollowers(uid);
			qNum = userHelper.countQuestions(uid);
			aNum = userHelper.countAnswers(uid);
			return 0;
		}

		@Override
		protected void onPostExecute(Integer res) {
			TextView nicknameTextView = (TextView) findViewById(R.id.user_info_nickname);
			TextView signatureTextView = (TextView) findViewById(R.id.user_info_signature);
			TextView followNumTextView = (TextView) findViewById(R.id.user_info_watch_people_num);
			TextView followerNumTextView = (TextView) findViewById(R.id.user_info_watch_byPeople_num);
			TextView qNumTextView = (TextView) findViewById(R.id.user_info_q_questioned_num);
			TextView aNumTextView = (TextView) findViewById(R.id.user_info_a_questioned_num);
			ImageView portraitImageView = (ImageView) findViewById(R.id.user_info_portrait);
            portraitImageView.setImageBitmap(AppUtility.getImage(u.getPortrait(), getApplicationContext()));
            nicknameTextView.setText(u.getNickname());
			signatureTextView.setText(u.getSignature());
			followNumTextView.setText(String.valueOf(followNum));
			followerNumTextView.setText(String.valueOf(followerNum));
			qNumTextView.setText(String.valueOf(qNum));
			aNumTextView.setText(String.valueOf(aNum));
		}

	}

	private class LoadProfileTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			currentUser = userHelper.getByUid(uid);
			return 0;
		}

		@Override
		protected void onPostExecute(Integer res) {
			TextView nicknameTextView = (TextView) findViewById(R.id.user_info_nickname);
			TextView signatureTextView = (TextView) findViewById(R.id.user_info_signature);
			nicknameTextView.setText(currentUser.getNickname());
			signatureTextView.setText(currentUser.getSignature());
			ImageView portraitImageView = (ImageView) findViewById(R.id.user_info_portrait);
            portraitImageView.setImageBitmap(AppUtility.getImage(currentUser.getPortrait(), getApplicationContext()));
        }

	}
}
