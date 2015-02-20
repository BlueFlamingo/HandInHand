package cn.edu.fudan.blueflamingo.handinhand;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.adapter.QuestionAdapter;
import cn.edu.fudan.blueflamingo.handinhand.middleware.QuestionHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.UserHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.ExQuestion;
import cn.edu.fudan.blueflamingo.handinhand.view.SwipeRefreshAndLoadLayout;


public class QuestionListFragment extends Fragment {

	private RecyclerView mRecyclerView;
	private QuestionAdapter questionAdapter;
	private List<ExQuestion> questions = new ArrayList<>();

	private QuestionHelper questionHelper = new QuestionHelper();
	private UserHelper userHelper = new UserHelper();
	private SwipeRefreshAndLoadLayout mSwipeLayout;
	private Activity parent;
	private LoadQuestionListTask loadQuestionListTask;

	//如果TID<0,则说明是从userInfo转过来的，因此取相反数之后的值就是UID
	private int TID;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_question_list, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		parent = getActivity();
		TID = parent.getIntent().getExtras().getInt("TID");
		Log.d("TID", String.valueOf(TID));

		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(1);

		questions.add(new ExQuestion(0,"Please wait...",0,0,0,(new Date()).getTime(),"","Loading...",temp,"",""));

		mRecyclerView = (RecyclerView)getActivity().findViewById(R.id.main_question_recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(parent));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setHasFixedSize(true);
		questionAdapter = new QuestionAdapter(parent, questions);
		questionAdapter.setOnItemClickListener(new QuestionAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent qItemIntent = new Intent(getActivity(), QuestionItemActivity.class);
				qItemIntent.putExtra("qid", questions.get(position).getId());
				qItemIntent.putExtra("uid", questions.get(position).getUid());
				qItemIntent.putExtra("title", questions.get(position).getTitle());
				qItemIntent.putExtra("content", questions.get(position).getContent());
				qItemIntent.putExtra("watchNum", questions.get(position).getScore1());
				qItemIntent.putExtra("MODE", QuestionItemActivity.FROM_QUESTION_LIST);
				startActivity(qItemIntent);
			}
		});
		mRecyclerView.setAdapter(questionAdapter);

		initRefresh();

		loadQuestionListTask = new LoadQuestionListTask();
		loadQuestionListTask.execute(TID);


	}

	private void initRefresh() {
		mSwipeLayout = (SwipeRefreshAndLoadLayout) parent.findViewById(R.id.main_question_swipe_container);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshAndLoadLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				(new LoadQuestionListTask()).execute(TID);
				/*
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						mSwipeLayout.setRefreshing(false);
						//refresh
					}
				}, 1000);*/
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
		mSwipeLayout.setmMode(SwipeRefreshAndLoadLayout.Mode.BOTH);
	}

	private class LoadQuestionListTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected void onPreExecute() {
			mSwipeLayout.setRefreshing(true);
		}

		@Override
		protected Integer doInBackground(Integer... params) {
			Integer tid = params[0];
			questions.clear();
			//如果TID<0,则说明是从userInfo转过来的，因此取相反数之后的值就是UID
			if (tid < 0) {
				questions.addAll(userHelper.getQuestions(-tid));
			} else {
				questions.addAll(questionHelper.getByTopic(tid));
			}
			Log.d("question size", String.valueOf(questions.size()));
			if (questions.size() > 0) {
				return 0;
			} else {
				return -1;
			}
		}

		@Override
		protected void onPostExecute(Integer result) {
			switch (result) {
				case 0:
					questionAdapter.notifyDataSetChanged();
					break;
				case -1:
					break;
			}
			mSwipeLayout.setRefreshing(false);

		}

	}

}
