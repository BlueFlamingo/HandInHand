package cn.edu.fudan.blueflamingo.handinhand;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initToolBar();
		initHomePageFragment();
		SharedPreferences mPrefs = getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
		SharedPreferences.Editor mEditor = mPrefs.edit();
		if (!mPrefs.getBoolean("loginFlag", false)) {
			Log.d("sharedPreferences", "not saved");
			mEditor.putBoolean("loginFlag", true);
		} else {
			Log.d("sharedPreferences", "saved true");
		}
		mEditor.apply();
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

			DrawerLayout mDrawerLayout;
			ActionBarDrawerToggle mDrawerToggle;
			mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
			mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
			mDrawerToggle.syncState();
			mDrawerLayout.setDrawerListener(mDrawerToggle);

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

		//for test
		String msg = "";
		switch (id) {
			case R.id.action_edit:
				msg += "Click edit";
				break;
			case R.id.action_search:
				msg += "Click search";
				break;
			default:
				break;
		}
		if (!msg.equals("")) {
			Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);
	}

}
