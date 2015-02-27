package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.middleware.QuestionHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.Question;
import cn.edu.fudan.blueflamingo.handinhand.view.MultiSelectSpinner;


public class QuestionEditActivity extends ActionBarActivity {

	private final String[] topics = {
			"30分钟紧急问答", "学海无涯", "校园生活", "情感大话",
			"职业发展", "吃喝玩乐", "其它"};
	private QuestionHelper questionHelper;
	private Global global;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_edit);
		questionHelper = new QuestionHelper();
		global = (Global) getApplication();
		//TODO:添加对是否是编辑模式的判断
		initToolbar(false);
		initMultiSelectionSpinner();
	}

	//@editFlag: true stands for edit mode, false stands for create(new) mode
	private void initToolbar(boolean editFlag) {
		Toolbar toolbar = (Toolbar) findViewById(R.id.question_edit_toolbar);
		if (toolbar != null) {
			if (editFlag) {
				toolbar.setTitle("编辑问题");
			} else {
				toolbar.setTitle("新建问题");
			}
			toolbar.inflateMenu(R.menu.menu_question_edit);
			setSupportActionBar(toolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	private void initMultiSelectionSpinner() {
		final MultiSelectSpinner multiSelectSpinner = (MultiSelectSpinner) findViewById(R.id.question_topic_spinner);
		multiSelectSpinner.setItems(topics);
		multiSelectSpinner.setSelection(0);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_question_edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		switch (id) {
			case R.id.action_save:
				MultiSelectSpinner multiSelectSpinner = (MultiSelectSpinner) findViewById(R.id.question_topic_spinner);
				List<Integer> res = multiSelectSpinner.getSelectedIndicies();
				String resString = "";
				ArrayList<Integer> topicArrayList = new ArrayList<>();
				Calendar createTime = Calendar.getInstance();
				//紧急区的时效
				Calendar tmpTime = (Calendar) createTime.clone();
				long expireTime = 0;
				for (Integer i : res) {
					resString += topics[i];
					if (i == 0) {
						expireTime = getExpireTime(tmpTime);
					}
					topicArrayList.add(i);
				}
				EditText titleEditText = (EditText) findViewById(R.id.question_edit_title);
				EditText contentEditText = (EditText) findViewById(R.id.question_edit_content);
				String title = titleEditText.getText().toString();
				String content = contentEditText.getText().toString();
				(new SendQuestionTask()).execute(title, content, topicArrayList, createTime.getTimeInMillis(), expireTime);
				Log.d("saved topics", resString);
				//返回
				if (getIntent().getExtras().getInt("mode") == 1) {
					Intent intent = new Intent(this, MainActivity.class);
					startActivity(intent);
				} else {
					finish();
				}
				break;
			case R.id.action_cancel:
				//直接返回
				finish();
				break;
			default:
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	private long getExpireTime(Calendar t) {
		t.add(Calendar.MINUTE, 30);
		return t.getTimeInMillis();
	}

	private class SendQuestionTask extends AsyncTask<Object, Integer, Integer> {

		@Override
		protected Integer doInBackground(Object... params) {
			String title = (String)params[0];
			String content = (String)params[1];
			ArrayList<Integer> topics = (ArrayList<Integer>) params[2];
			long createTime = (long) params[3];
			long expireTime = (long) params[4];
			Question q = new Question(title, content, global.getUid(), createTime, expireTime, topics);
			Log.d("add question", String.valueOf(global.getUid()));
			Log.d("add question", (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(createTime));
			questionHelper.add(q);
			return 0;
		}

		@Override
		protected void onPostExecute(Integer res) {
			Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
		}

	}
}
