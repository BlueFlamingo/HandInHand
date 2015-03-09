package cn.edu.fudan.blueflamingo.handinhand;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;


/**
 * The type Auth activity.
 */
public class AuthActivity extends ActionBarActivity {

	private Global globalVal;

	private FragmentManager fragmentManager;

    /**
     * The constant HANG_OUT.
     */
    public final static int HANG_OUT = 1;
    /**
     * The constant REGISTERED.
     */
    public final static int REGISTERED = 2;
    /**
     * The constant LOGINED.
     */
    public final static int LOGINED = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		globalVal = (Global) getApplication();
		fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.auth_fragment, new AuthFragment(), "auth_main")
				.commit();
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (fragmentManager.getBackStackEntryCount() != 0) {
				fragmentManager.popBackStack();
			}
			return true;
		} else
			return super.onKeyDown(keyCode, event);
	}
}
