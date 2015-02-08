package cn.edu.fudan.blueflamingo.handinhand;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class AnswerEditActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_edit);
		//TODO:增加对回答编辑模式的判断
		initToolbar(false);

	}

	//@editFlag: true stands for edit mode, false stands for create(new) mode
	private void initToolbar(boolean editFlag) {
		Toolbar toolbar = (Toolbar) findViewById(R.id.answer_edit_toolbar);
		if (toolbar != null) {
			if (editFlag) {
				toolbar.setTitle("编辑你的回答");
			} else {
				toolbar.setTitle("新建一个回答");
			}
			toolbar.inflateMenu(R.menu.menu_answer_edit);
			setSupportActionBar(toolbar);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_answer_edit, menu);
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
				//TODO:增加回答保存逻辑
				Toast.makeText(this, "save!", Toast.LENGTH_SHORT).show();
				finish();
				break;
			case R.id.action_cancel:
				Toast.makeText(this, "canceled!", Toast.LENGTH_SHORT).show();
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
