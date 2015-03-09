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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.fudan.blueflamingo.handinhand.lib.AppUtility;
import cn.edu.fudan.blueflamingo.handinhand.middleware.CommentHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.QuestionHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.ScoreHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.ExQuestion;


/**
 * The type Answer item activity.
 */
public class AnswerItemActivity extends ActionBarActivity {

	private int AID = -1;
	private int QID = -1;
	private Global global;
	private int approvNum;

	private CommentHelper commentHelper = new CommentHelper();
	private ScoreHelper scoreHelper = new ScoreHelper();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_item);
		initToolbar();
		global = (Global) getApplication();
		AID = getIntent().getExtras().getInt("aid");
		QID = getIntent().getExtras().getInt("qid");
		String qTitle = getIntent().getExtras().getString("title");
		String content = getIntent().getExtras().getString("content");
		approvNum = getIntent().getExtras().getInt("approvNum");
		String nickname = getIntent().getExtras().getString("nickname");
		final int uid = getIntent().getExtras().getInt("uid");
		String portraitStr = getIntent().getExtras().getString("portrait");
		//设置回答的主体
		TextView titleTextView = (TextView) findViewById(R.id.answer_item_title);
		TextView contentTextView = (TextView) findViewById(R.id.answer_item_content);
		TextView approvNumTextView = (TextView) findViewById(R.id.answer_item_approve_num);
		TextView nicknameTextView = (TextView) findViewById(R.id.answer_item_username);
		ImageView portraitImageView = (ImageView) findViewById(R.id.answer_item_portrait);
		portraitImageView.setImageBitmap(AppUtility.getImage(portraitStr,this));
		titleTextView.setText(qTitle);
		contentTextView.setText(content);
		approvNumTextView.setText(String.valueOf(approvNum));
		nicknameTextView.setText(nickname);

		(new LoadTask()).execute(AID, QID);

		//bind approve
		RelativeLayout approveContainer = (RelativeLayout) findViewById(R.id.answer_item_approve_container);
		approveContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				(new ApprovTask()).execute(AID, global.getUid());
			}
		});

		//bind comment
		RelativeLayout commentContainer = (RelativeLayout) findViewById(R.id.answer_item_comment_container);
		commentContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent commentListIntent = new Intent(AnswerItemActivity.this, CommentListActivity.class);
				commentListIntent.putExtra("aid", AID);
				startActivity(commentListIntent);
			}
		});

		//bind jump to user action
		RelativeLayout userContainer = (RelativeLayout) findViewById(R.id.answer_item_user_container);
		userContainer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent userIntent = new Intent(AnswerItemActivity.this, UserInfoActivity.class);
				userIntent.putExtra("uid", uid);
				startActivity(userIntent);
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

	private class LoadTask extends AsyncTask<Integer, Integer, Integer> {

		private String title;

		@Override
		protected Integer doInBackground(Integer... params) {
			int aid = params[0];
			int qid = params[1];
			QuestionHelper questionHelper = new QuestionHelper();
			ExQuestion exQuestion = questionHelper.getByQid(qid);
			title = exQuestion.getTitle();
			return commentHelper.countComment(aid);
		}

		@Override
		protected void onPostExecute(Integer res) {
			TextView commentNumTextView = (TextView) findViewById(R.id.answer_item_comment_num);
			TextView titleTextView = (TextView) findViewById(R.id.answer_item_title);
			titleTextView.setText(title);
			commentNumTextView.setText(String.valueOf(res));
		}

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
