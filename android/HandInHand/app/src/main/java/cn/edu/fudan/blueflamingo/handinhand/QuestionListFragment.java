package cn.edu.fudan.blueflamingo.handinhand;


import android.app.Activity;
import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.adapter.QuestionAdapter;
import cn.edu.fudan.blueflamingo.handinhand.model.Question;
import cn.edu.fudan.blueflamingo.handinhand.view.SwipeRefreshAndLoadLayout;


public class QuestionListFragment extends Fragment {

	private RecyclerView mRecyclerView;
	private QuestionAdapter questionAdapter;
	private List<Question> questions = new ArrayList<>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		ActionBarActivity parent = (ActionBarActivity)getActivity();
		try {
			parent.getSupportActionBar().setTitle(R.string.hangout);
			Log.d("getActionBar", "success");
		} catch (Exception e) {
			Log.d("getActionBar", "null");
		}


		return inflater.inflate(R.layout.fragment_question_list, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		Activity parent = getActivity();
		ArrayList<Integer> topic = new ArrayList<>();
		topic.add(1);
		questions.add(new Question(0,"this is a test",0,0,"test title",topic));

		mRecyclerView = (RecyclerView)getActivity().findViewById(R.id.main_question_recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(parent));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setHasFixedSize(true);
		questionAdapter = new QuestionAdapter(parent, questions);
		mRecyclerView.setAdapter(questionAdapter);

		final SwipeRefreshAndLoadLayout mSwipeLayout;
		mSwipeLayout = (SwipeRefreshAndLoadLayout) parent.findViewById(R.id.main_question_swipe_container);
		mSwipeLayout.setOnRefreshListener(new SwipeRefreshAndLoadLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						mSwipeLayout.setRefreshing(false);
						//refresh
					}
				}, 1000);
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

}