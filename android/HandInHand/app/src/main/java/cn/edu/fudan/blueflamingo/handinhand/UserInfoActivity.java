package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class UserInfoActivity extends ActionBarActivity {

	Global global;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		initToolbar();
		global = (Global) getApplication();
		RelativeLayout userAsked = (RelativeLayout) findViewById(R.id.user_info_q_container);
		userAsked.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent qListIntent = new Intent(UserInfoActivity.this, QuestionListActivity.class);
				qListIntent.putExtra("TOPIC", "问过的问题");
				qListIntent.putExtra("TID", -global.getUid());
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

		initIFav();
		initFavdMe();
	}

	private void initIFav() {
		final RelativeLayout iFav = (RelativeLayout) findViewById(R.id.user_info_i_fav_container);
		iFav.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent iFavIntent = new Intent(UserInfoActivity.this, UserListActivity.class);
				iFavIntent.putExtra("MODE", UserListActivity.I_FAVORITE);
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
				favMeIntent.putExtra("MODE", UserListActivity.I_FAVORITE);
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_user_info, menu);
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
				Intent userEditIntent = new Intent(this, UserEditActivity.class);
				startActivity(userEditIntent);

		}

		return super.onOptionsItemSelected(item);
	}
}
