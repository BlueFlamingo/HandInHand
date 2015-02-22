package cn.edu.fudan.blueflamingo.handinhand;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BlankFragment extends Fragment {

	public BlankFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_blank, container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		final Global global = (Global) getActivity().getApplication();
		TextView text = (TextView) getActivity().findViewById(R.id.blank_add_words);
		String baseStr = "还没有";
		String tailStr = "呢！快去添加一个吧！";
		String modeStr = "问题";
		String res = baseStr + modeStr + tailStr;
		text.setText(res);
		text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (global.getLoginFlag()) {
					Intent intent = new Intent(getActivity(), QuestionEditActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(getActivity(), "请先登录后再发布问题", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
