package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.fudan.blueflamingo.handinhand.middleware.AnswerHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.CommentHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.FavoriteHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.ScoreHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.ExAnswer;


public class AnswerItemActivity extends ActionBarActivity {

	private int AID = -1;
	private Global global;
	private int approvNum;

	private CommentHelper commentHelper = new CommentHelper();
	private AnswerHelper answerHelper = new AnswerHelper();
	private FavoriteHelper favoriteHelper = new FavoriteHelper();
	private ScoreHelper scoreHelper = new ScoreHelper();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_item);
		initToolbar();
		global = (Global) getApplication();
		AID = getIntent().getExtras().getInt("aid");
		String qTitle = getIntent().getExtras().getString("title");
		String content = getIntent().getExtras().getString("content");
		approvNum = getIntent().getExtras().getInt("approvNum");
		String nickname = getIntent().getExtras().getString("nickname");
		//设置回答的主体
		TextView titleTextView = (TextView) findViewById(R.id.answer_item_title);
		TextView contentTextView = (TextView) findViewById(R.id.answer_item_content);
		TextView approvNumTextView = (TextView) findViewById(R.id.answer_item_approve_num);
		TextView nicknameTextView = (TextView) findViewById(R.id.answer_item_username);
		titleTextView.setText(qTitle);
		contentTextView.setText(content);
		approvNumTextView.setText(String.valueOf(approvNum));
		nicknameTextView.setText(nickname);

		//bind approve
		RelativeLayout approveContainer = (RelativeLayout) findViewById(R.id.answer_item_approve_container);
		approveContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				(new ApprovTask()).execute(AID, global.getUid());
			}
		});
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

	private class ApprovTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			int aid = params[0];
			int uid = params[1];
			return scoreHelper.switchApprove(uid, aid);
		}

		@Override
		protected void onPostExecute(Integer res) {
			TextView approvNumTextView = (TextView) findViewById(R.id.answer_item_approve_num);
			switch (res) {
				case 0:
					approvNumTextView.setText(String.valueOf(--approvNum));
					Log.d("answer score1", String.valueOf(approvNum));
					Toast.makeText(getApplicationContext(), "取消赞", Toast.LENGTH_SHORT).show();
					break;
				case 1:
					approvNumTextView.setText(String.valueOf(++approvNum));
					Log.d("answer score1", String.valueOf(approvNum));
					Toast.makeText(getApplicationContext(), "赞了此问题", Toast.LENGTH_SHORT).show();
					break;
			}
		}
	}
}
