package cn.edu.fudan.blueflamingo.handinhand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.R;
import cn.edu.fudan.blueflamingo.handinhand.model.Answer;
import cn.edu.fudan.blueflamingo.handinhand.model.AnswerHeader;

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

	private List<Answer> answers;
	private AnswerHeader answerHeader;

	private Context mContext;

	private static final int TYPE_HEADER = 0;
	private static final int TYPE_ITEM = 1;

	public AnswerAdapter(Context context, List<Answer> answers, AnswerHeader answerHeader) {
		this.mContext = context;
		this.answers = answers;
		this.answerHeader = answerHeader;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		if (i == TYPE_ITEM) {
			View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.answer_item, viewGroup, false);
			return new VHItem(v);
		} else if (i == TYPE_HEADER) {
			View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.answer_header, viewGroup, false);
			return new VHHeader(v);
		}
		throw new RuntimeException("There is no type that matches the type");
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
		if (viewHolder instanceof VHItem) {
			Answer a = answers.get(i - 1);	//由于header占了第0个位置
			//TODO:设置username、头像、点赞数、时间
			VHItem item = (VHItem) viewHolder;
			item.abstractTextView.setText(a.getContent());
		} else if (viewHolder instanceof VHHeader) {
			VHHeader header = (VHHeader) viewHolder;
			header.titleTextView.setText(answerHeader.getTitle());
			header.descriptionTextView.setText(answerHeader.getDescription());
			header.watchNumTextView.setText(String.valueOf(answerHeader.getWatchNum()));
		}

	}

	@Override
	public int getItemCount() {
		return answers == null ? 1 : answers.size() + 1;
	}

	@Override
	public int getItemViewType(int position) {
		if (position == 0) {
			return TYPE_HEADER;
		} else {
			return TYPE_ITEM;
		}
	}

	class VHItem extends RecyclerView.ViewHolder {
		public TextView usernameTextView;
		public ImageView headImageView;
		public TextView approveNumTextView;
		public TextView abstractTextView;
		public TextView timeTextView;

		public VHItem(View v) {
			super(v);
			usernameTextView = (TextView) v.findViewById(R.id.answer_user_name);
			headImageView = (ImageView) v.findViewById(R.id.answer_user_head);
			approveNumTextView = (TextView) v.findViewById(R.id.answer_approve_num);
			abstractTextView = (TextView) v.findViewById(R.id.answer_abstract);
			timeTextView = (TextView) v.findViewById(R.id.answer_time);
		}
	}

	class VHHeader extends RecyclerView.ViewHolder {
		public TextView titleTextView;
		public TextView descriptionTextView;
		public TextView watchNumTextView;

		public VHHeader(View v) {
			super(v);
			titleTextView = (TextView) v.findViewById(R.id.question_item_title);
			descriptionTextView = (TextView) v.findViewById(R.id.question_item_description);
			watchNumTextView = (TextView) v.findViewById(R.id.question_item_watch_num);
		}
	}
}
