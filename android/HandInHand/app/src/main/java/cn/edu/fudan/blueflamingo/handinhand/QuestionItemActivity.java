package cn.edu.fudan.blueflamingo.handinhand;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


/**
 * The type Question item activity.
 */
public class QuestionItemActivity extends ActionBarActivity {

	private int QID = -1;

    /**
     * The constant MODE.
     */
    public static int MODE = 0;

    /**
     * The constant FROM_QUESTION_LIST.
     */
    public final static int FROM_QUESTION_LIST = 0;
    /**
     * The constant FROM_USER_ASKED_LIST.
     */
    public final static int FROM_USER_ASKED_LIST = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_item);
		QID = getIntent().getExtras().getInt("qid");
		MODE = getIntent().getExtras().getInt("MODE");
		initToolbar();
		initAnswerListFragment();
	}

	private void initAnswerListFragment() {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.answer_list_fragment, new AnswerListFragment())
				.commit();
	}

	private void initToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.question_item_toolbar);
		if (toolbar != null) {
			toolbar.inflateMenu(R.menu.menu_question_item);
			setSupportActionBar(toolbar);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_question_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if (MODE == QuestionItemActivity.FROM_QUESTION_LIST) {
			switch (id) {
				case R.id.action_write_answer:
					Toast.makeText(this, "write answer", Toast.LENGTH_SHORT).show();
					Intent writeIntent = new Intent(this, AnswerEditActivity.class);
					writeIntent.putExtra("qid", QID);
					startActivity(writeIntent);
			}
		}

		return super.onOptionsItemSelected(item);
	}
}
