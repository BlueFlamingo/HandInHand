package cn.edu.fudan.blueflamingo.handinhand;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import cn.edu.fudan.blueflamingo.handinhand.middleware.AnswerHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.Answer;


public class AnswerEditActivity extends ActionBarActivity {

	private AnswerHelper answerHelper;
	private Global global;
	private int QID = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_edit);
		//TODO:增加对回答编辑模式的判断
		initToolbar(false);
		global = (Global) getApplication();
		answerHelper = new AnswerHelper();
		QID = getIntent().getExtras().getInt("qid");
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
				EditText contentEditText = (EditText) findViewById(R.id.answer_editor);
				String content = contentEditText.getText().toString();
				(new SendAnswerTask()).execute(content);
				break;
			case R.id.action_cancel:
				Toast.makeText(this, "canceled!", Toast.LENGTH_SHORT).show();
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	private class SendAnswerTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			String content = params[0];
			Answer a = new Answer(content, global.getUid(), QID, (new Date()).getTime());
			int aid = answerHelper.add(a);
			Log.d("answer add", String.valueOf(aid));
			return true;
		}

		@Override
		protected void onPostExecute(Boolean res) {
			if (res) {
				Toast.makeText(getApplicationContext(), "save!", Toast.LENGTH_SHORT).show();
				finish();
			}
		}

	}
}
