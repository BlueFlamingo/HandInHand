package cn.edu.fudan.blueflamingo.handinhand;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class QuestionListActivity extends ActionBarActivity {

	private String TOPIC = "随便看看";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_list);
		TOPIC = getIntent().getExtras().getString("TOPIC");
		initToolbar();
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
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.question_list_fragment, new QuestionListFragment())
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
				Intent newIntent = new Intent(this, QuestionEditActivity.class);
				newIntent.putExtra("TOPIC", TOPIC);
				startActivity(newIntent);
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
