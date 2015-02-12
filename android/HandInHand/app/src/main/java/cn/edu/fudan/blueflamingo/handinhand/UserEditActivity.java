package cn.edu.fudan.blueflamingo.handinhand;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cn.edu.fudan.blueflamingo.handinhand.middleware.UserHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.User;


public class UserEditActivity extends ActionBarActivity {

	private final static String[] genderArray = {"男", "女"};
	private int uid;
	private UserHelper userHelper = new UserHelper();
	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_edit);
		initToolbar();
		uid = getIntent().getExtras().getInt("uid");
		(new LoadProfileTask()).execute();
	}

	private void initToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.user_edit_toolbar);
		if (toolbar != null) {
			toolbar.setTitle("编辑个人信息");
			toolbar.inflateMenu(R.menu.menu_user_edit);
			setSupportActionBar(toolbar);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_user_edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id) {
			case R.id.action_save:
				(new SaveProfileTask()).execute();
				break;
			case R.id.action_cancel:
				finish();
		}

		return super.onOptionsItemSelected(item);
	}

	private class LoadProfileTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			currentUser = userHelper.getByUid(uid);
			return 0;
		}

		@Override
		protected void onPostExecute(Integer res) {
			//TODO:设置头像
			EditText nicknameEditText = (EditText) findViewById(R.id.user_edit_nickname);
			Spinner genderSpinner = (Spinner) findViewById(R.id.user_edit_gender_spinner);
			EditText signatureEditText = (EditText) findViewById(R.id.user_edit_signature);
			nicknameEditText.setText(currentUser.getNickname());
			genderSpinner.setSelection(currentUser.getMale());
			signatureEditText.setText(currentUser.getSignature());
		}

	}

	private class SaveProfileTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			EditText nicknameEditText = (EditText) findViewById(R.id.user_edit_nickname);
			Spinner genderSpinner = (Spinner) findViewById(R.id.user_edit_gender_spinner);
			EditText signatureEditText = (EditText) findViewById(R.id.user_edit_signature);
			String nickname = nicknameEditText.getText().toString();
			int gender = genderSpinner.getSelectedItemPosition();
			String signature = signatureEditText.getText().toString();
			currentUser.nickname = nickname;
			currentUser.male = gender;
			currentUser.signature = signature;
			return userHelper.update(currentUser);
		}

		@Override
		protected void onPostExecute(Integer res) {
			if (res != 0) {
				Toast.makeText(getApplicationContext(),"保存成功！",Toast.LENGTH_SHORT).show();
				finish();
			}
		}

	}
}
