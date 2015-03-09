package cn.edu.fudan.blueflamingo.handinhand;

import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import cn.edu.fudan.blueflamingo.handinhand.lib.AppUtility;


/**
 * The type Question list activity.
 */
public class QuestionListActivity extends ActionBarActivity {

	private String TOPIC = "随便看看";

    /**
     * The TID.
     */
    public int TID = 0;
	private Global globalVal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_list);
		TOPIC = getIntent().getExtras().getString("TOPIC");
		TID = getIntent().getExtras().getInt("TID");
		globalVal = (Global) getApplication();
		initToolbar();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initQuestionListFragment();
	}

	private void initToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.question_list_toolbar);
		if (toolbar != null) {
			toolbar.setTitle(TOPIC);
			toolbar.inflateMenu(R.menu.menu_question_list);
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	private void initQuestionListFragment() {
		QuestionListFragment questionListFragment = new QuestionListFragment();
		questionListFragment.setOnListEmptyListener(new QuestionListFragment.OnListEmptyListener() {
			@Override
			public void onListEmpty() {
				FragmentManager fragmentManager = getSupportFragmentManager();
                if (TID != AppUtility.FAV_QUESTION_LIST) {
                    fragmentManager.beginTransaction()
                            .replace(R.id.question_list_fragment, new BlankFragment())
                            .commit();
                }
				//Toast.makeText(getApplicationContext(), "list empty!", Toast.LENGTH_SHORT).show();
			}
		});
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.question_list_fragment, questionListFragment)
				.commit();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_question_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
			case R.id.action_question_edit:
				if (globalVal.getLoginFlag()) {
					//jump to QuestionEditActivity
					Intent newIntent = new Intent(this, QuestionEditActivity.class);
					newIntent.putExtra("TOPIC", TOPIC);
					startActivity(newIntent);
				} else {
					Toast.makeText(this, "请先登录后再发布问题", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.action_question_search:
				Toast.makeText(this, "search clicked!", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
