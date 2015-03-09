package cn.edu.fudan.blueflamingo.handinhand;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.adapter.CommentAdapter;
import cn.edu.fudan.blueflamingo.handinhand.middleware.CommentHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.Comment;
import cn.edu.fudan.blueflamingo.handinhand.model.ExComment;
import cn.edu.fudan.blueflamingo.handinhand.model.Utility;
import cn.edu.fudan.blueflamingo.handinhand.view.SwipeRefreshAndLoadLayout;


/**
 * The type Comment list activity.
 */
public class CommentListActivity extends ActionBarActivity {

	private RecyclerView mRecyclerView;
	private CommentAdapter commentAdapter;
	private List<ExComment> comments = new ArrayList<>();
	private CommentHelper commentHelper = new CommentHelper();
	private SwipeRefreshAndLoadLayout mSwipeLayout;
	private Global global;
	private int AID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment_list);
		AID = getIntent().getExtras().getInt("aid");
		global = (Global) getApplication();
		initToolbar();

		comments.add(new ExComment("Please wait...", "Loading..."));

		mRecyclerView = (RecyclerView) findViewById(R.id.comment_item_recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setHasFixedSize(true);
		commentAdapter = new CommentAdapter(this, comments);
		mRecyclerView.setAdapter(commentAdapter);

		initLoadAndRefresh();

		(new LoadCommentListTask()).execute();

		ButtonFlat btn_send = (ButtonFlat) findViewById(R.id.comment_btn_send);
		btn_send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText text = (EditText) findViewById(R.id.comment_editor_content);
				String content = text.getText().toString();
				(new SendCommentTask()).execute(content);
			}
		});
	}

	private void initLoadAndRefresh() {

		mSwipeLayout = (SwipeRefreshAndLoadLayout) findViewById(R.id.comment_item_container);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshAndLoadLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				(new LoadCommentListTask()).execute();
			}

			@Override
			public void onLoadMore() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						mSwipeLayout.setRefreshing(false);
						//load more
					}
				}, 1000);
			}
		});
		mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
		mSwipeLayout.setmMode(SwipeRefreshAndLoadLayout.Mode.PULL_FROM_START);
	}

	private void initToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.comment_list_toolbar);
		if (toolbar != null) {
			toolbar.setTitle("评论列表");
			toolbar.inflateMenu(R.menu.menu_comment_list);
			setSupportActionBar(toolbar);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_comment_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		return super.onOptionsItemSelected(item);
	}

	private class SendCommentTask extends AsyncTask<String, Integer, Integer> {

		@Override
		protected void onPreExecute() {
			mSwipeLayout.setRefreshing(true);
		}

		@Override
		protected Integer doInBackground(String... params) {
			String content = params[0];
			Comment comment = new Comment(global.getUid(), (new Date()).getTime(), content, AID);
			int cid = commentHelper.add(comment);
			Log.d("comment add", String.valueOf(cid));
			comments.add(Utility.commentToExComment(comment));
			return 0;
		}

		@Override
		protected void onPostExecute(Integer res) {
			commentAdapter.notifyDataSetChanged();
			Toast.makeText(getApplicationContext(), "发布成功", Toast.LENGTH_SHORT).show();
			EditText text = (EditText) findViewById(R.id.comment_editor_content);
			text.setText("");
			mSwipeLayout.setRefreshing(false);
		}

	}

	private class LoadCommentListTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected void onPreExecute() {
			mSwipeLayout.setRefreshing(true);
		}

		@Override
		protected Integer doInBackground(Integer... params) {
			comments.clear();
			comments.addAll(commentHelper.getByAid(AID));
			if (comments.size() > 0) {
				return 0;
			} else {
				return -1;
			}
		}

		@Override
		protected void onPostExecute(Integer res) {
			switch (res) {
				case 0:
					commentAdapter.notifyDataSetChanged();
					break;
				case 1:
					break;
			}
			mSwipeLayout.setRefreshing(false);
		}

	}
}
