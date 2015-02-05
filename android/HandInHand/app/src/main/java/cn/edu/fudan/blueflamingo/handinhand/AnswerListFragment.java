package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.adapter.AnswerAdapter;
import cn.edu.fudan.blueflamingo.handinhand.adapter.SimpleAnswerAdapter;
import cn.edu.fudan.blueflamingo.handinhand.model.Answer;
import cn.edu.fudan.blueflamingo.handinhand.model.AnswerHeader;
import cn.edu.fudan.blueflamingo.handinhand.view.SwipeRefreshAndLoadLayout;

public class AnswerListFragment extends Fragment {

	private RecyclerView mRecyclerView;
	private AnswerAdapter answerAdapter;
	private List<Answer> answers = new ArrayList<>();
	private AnswerHeader answerHeader;

	private int MODE = 0;

	public AnswerListFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_answer_list, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		ActionBarActivity parent = (ActionBarActivity) getActivity();

		int QID = parent.getIntent().getExtras().getInt("qid");
		MODE = parent.getIntent().getExtras().getInt("MODE");

		Log.d("q item", String.valueOf(QID));

		answers.add(new Answer("I am a test answer!!!", 0));
		answerHeader = new AnswerHeader("I am test question title",
				"I am a test question description", 123);

		setToolbarTitle(answers.size(), parent);

		mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.question_answer_recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(parent));
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setHasFixedSize(true);
		if (MODE == QuestionItemActivity.FROM_QUESTION_LIST) {
			answerAdapter = new AnswerAdapter(parent, answers, answerHeader);
			answerAdapter.setOnItemClickListener(new AnswerAdapter.OnItemClickListener() {
				@Override
				public void onItemClick(View view, int position) {
					Intent aItemIntent = new Intent(getActivity(), AnswerItemActivity.class);
					aItemIntent.putExtra("aid", answers.get(position - 1).getId());
					startActivity(aItemIntent);
				}
			});
			mRecyclerView.setAdapter(answerAdapter);
		} else {
			SimpleAnswerAdapter simpleAnswerAdapter = new SimpleAnswerAdapter(parent, answers);
			simpleAnswerAdapter.setOnItemClickListener(new SimpleAnswerAdapter.OnItemClickListener() {
				@Override
				public void onItemClick(View view, int position) {
					Intent aItemIntent = new Intent(getActivity(), AnswerItemActivity.class);
					aItemIntent.putExtra("aid", answers.get(position).getId());
					startActivity(aItemIntent);
				}
			});
			mRecyclerView.setAdapter(simpleAnswerAdapter);
		}



		initLoadAndRefresh(parent);
	}

	private void initLoadAndRefresh(ActionBarActivity parent) {
		final SwipeRefreshAndLoadLayout mSwipeLayout;
		mSwipeLayout = (SwipeRefreshAndLoadLayout) parent.findViewById(R.id.question_item_container);
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

	private void setToolbarTitle(int n, ActionBarActivity parent) {
		parent.getSupportActionBar().setTitle("共有" + String.valueOf(n) + "个回答");
	}
}
