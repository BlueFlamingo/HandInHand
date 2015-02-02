package cn.edu.fudan.blueflamingo.handinhand;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.view.MultiSelectSpinner;


public class QuestionEditActivity extends ActionBarActivity {

	private final String[] topics = {
			"学海无涯", "校园生活", "情感大话",
			"职业发展", "社团活动", "吃喝玩乐",
			"今日热门", "主编推荐", "其它"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_edit);
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
				//TODO:保存
				MultiSelectSpinner multiSelectSpinner = (MultiSelectSpinner) findViewById(R.id.question_topic_spinner);
				List<Integer> res = multiSelectSpinner.getSelectedIndicies();
				String resString = "";
				for (Integer i : res) {
					resString += topics[i];
				}
				Toast.makeText(getApplicationContext(), resString, Toast.LENGTH_LONG).show();
				//返回
				finish();
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
}
