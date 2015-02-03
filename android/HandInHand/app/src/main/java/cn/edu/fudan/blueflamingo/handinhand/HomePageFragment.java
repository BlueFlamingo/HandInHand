package cn.edu.fudan.blueflamingo.handinhand;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.os.Handler;

import java.security.spec.ECField;

import cn.edu.fudan.blueflamingo.handinhand.view.SwipeRefreshAndLoadLayout;

public class HomePageFragment extends Fragment {

	//cat_top:今日热门
	//cat_1:学海无涯
	//cat_2:校园生活
	//cat_3:情感大话
	//cat_4:职业发展
	//cat_5:吃喝玩乐
	//cat_6:其它
	private final String[] topics = {
			"学海无涯", "校园生活", "情感大话",
			"职业发展", "吃喝玩乐", "其它",
			"今日热门"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_page, container, false);

		initTopicListener(v);
		initRefreshLoad(v);
		initToolbar();
		return v;
	}

	private void initTopicOnClickListener(View v, int topic, final String topicString) {
		int RTopic = topicMapper(topic);
		FrameLayout frameLayout = (FrameLayout) v.findViewById(RTopic);
		frameLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent qListIntent = new Intent(getActivity(), QuestionListActivity.class);
				qListIntent.putExtra("TOPIC",topicString);
				startActivity(qListIntent);
			}
		});
	}

	private void initTopicListener(View v) {
		for (int i = 0; i < topics.length; i++) {
			initTopicOnClickListener(v, i, topics[i]);
		}
	}

	private void initRefreshLoad(View v) {
		final SwipeRefreshAndLoadLayout mSwipeLayout;
		mSwipeLayout = (SwipeRefreshAndLoadLayout) v.findViewById(R.id.main_swipe_container);
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
		mSwipeLayout.setmMode(SwipeRefreshAndLoadLayout.Mode.PULL_FROM_START);
	}

	private void initToolbar() {
		ActionBarActivity parent = (ActionBarActivity) getActivity();
		try {
			parent.getSupportActionBar().setTitle("首页");
			parent.getSupportActionBar().setSubtitle("欢迎！");
		} catch (Exception e) {
			Log.d("HomePageFragment", "null toolbar");
		}
	}

	private int topicMapper(int topicId) {
		switch (topicId) {
			case 6:
				return R.id.cat_top;
			case 0:
				return R.id.cat_1;
			case 1:
				return R.id.cat_2;
			case 2:
				return R.id.cat_3;
			case 3:
				return R.id.cat_4;
			case 4:
				return R.id.cat_5;
			case 5:
				return R.id.cat_6;
			default:
				return -1;
		}
	}

}
