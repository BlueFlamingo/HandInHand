package cn.edu.fudan.blueflamingo.handinhand;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class QuestionItemActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_item);
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

		switch (id) {
			case R.id.action_write_answer:
				//TODO:跳转到编辑答案界面
				Toast.makeText(this, "write answer", Toast.LENGTH_SHORT).show();
		}

		return super.onOptionsItemSelected(item);
	}
}
