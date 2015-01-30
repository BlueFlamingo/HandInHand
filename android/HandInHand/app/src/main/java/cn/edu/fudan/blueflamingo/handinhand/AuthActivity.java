package cn.edu.fudan.blueflamingo.handinhand;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class AuthActivity extends ActionBarActivity {

	private global globalVal;

	private final static int AUTH_ACTIVITY = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		globalVal = (global) getApplication();
		bindBtnAction();
	}

	private void bindBtnAction() {
		com.gc.materialdesign.views.ButtonRectangle btn_login
				= (com.gc.materialdesign.views.ButtonRectangle) findViewById(R.id.auth_btn_login);
		com.gc.materialdesign.views.ButtonRectangle btn_logout
				= (com.gc.materialdesign.views.ButtonRectangle) findViewById(R.id.auth_btn_logout);
		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				authLogin();
			}
		});
		btn_logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				authLogout();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_auth, menu);
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

	private void authLogin() {
		globalVal.setLoginFlag(true);
		setResult(Activity.RESULT_OK);
		finish();
	}

	private void authLogout() {
		globalVal.setLoginFlag(false);
		setResult(Activity.RESULT_OK);
		finish();
	}
}
