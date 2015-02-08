package cn.edu.fudan.blueflamingo.handinhand;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;


public class UserEditActivity extends ActionBarActivity {

	private final static String[] genderArray = {"男", "女"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_edit);
		initToolbar();

		Spinner spinner = (Spinner) findViewById(R.id.user_edit_gender_spinner);

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
				//TODO:save user profile
				break;
			case R.id.action_cancel:
				finish();

		}

		return super.onOptionsItemSelected(item);
	}
}
