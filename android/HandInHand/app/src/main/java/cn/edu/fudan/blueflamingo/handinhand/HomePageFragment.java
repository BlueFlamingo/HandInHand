package cn.edu.fudan.blueflamingo.handinhand;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.os.Handler;

import cn.edu.fudan.blueflamingo.handinhand.view.SwipeRefreshAndLoadLayout;

public class HomePageFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home_page, container, false);
		FrameLayout top = (FrameLayout) v.findViewById(R.id.cat_top);
		top.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.main_fragment, new QuestionListFragment())
						.addToBackStack("main_fragment")
						.commit();
			}
		});

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
		return v;
	}

}
