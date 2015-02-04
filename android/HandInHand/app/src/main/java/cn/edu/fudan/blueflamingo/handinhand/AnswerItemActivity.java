package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class AnswerItemActivity extends ActionBarActivity {

	private int AID = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_item);
		initToolbar();
		AID = getIntent().getExtras().getInt("aid");
		//TODO:在这里设置回答的主体
	}

	private void initToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.answer_item_toolbar);
		if (toolbar != null) {
			toolbar.setTitle("回答");
			toolbar.inflateMenu(R.menu.menu_answer_item);
			setSupportActionBar(toolbar);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_answer_item, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id) {
			case R.id.action_show_comment:
				Intent commentListIntent = new Intent(this, CommentListActivity.class);
				commentListIntent.putExtra("aid", AID);
				startActivity(commentListIntent);
				break;
			case R.id.action_back:
				finish();
				break;
		}

		return super.onOptionsItemSelected(item);
	}
}
