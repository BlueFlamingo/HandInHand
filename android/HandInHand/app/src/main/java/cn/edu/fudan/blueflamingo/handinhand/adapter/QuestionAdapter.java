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
import cn.edu.fudan.blueflamingo.handinhand.model.Question;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
		this.mOnItemClickListener = mOnItemClickListener;
	}

	private List<Question> questions;

	private Context mContext;

	public QuestionAdapter(Context context, List<Question> questions) {
		this.mContext = context;
		this.questions = questions;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.question_item, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
		Question q = questions.get(i);
		viewHolder.titleTextView.setText(q.getTitle());
		//TODO:设置头像
		//viewHolder.headImageView.setImageBitmap();
		viewHolder.watchNumTextView.setText(String.valueOf(q.getScore1()));
		if (q.getContent().length() > 30) {
			viewHolder.abstractTextView.setText(q.getContent().substring(0, 30));
		} else {
			viewHolder.abstractTextView.setText(q.getContent());
		}
		viewHolder.timeTextView.setText(String.valueOf(q.getCreatedTime()));

		//如果设置了回调则设置点击事件
		if (mOnItemClickListener != null) {
			viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mOnItemClickListener.onItemClick(viewHolder.itemView, i);
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return questions == null ? 0 : questions.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView titleTextView;
		public ImageView headImageView;
		public TextView watchNumTextView;
		public TextView abstractTextView;
		public TextView timeTextView;

		public ViewHolder(View v) {
			super(v);
			titleTextView = (TextView) v.findViewById(R.id.question_title);
			headImageView = (ImageView) v.findViewById(R.id.question_user_head);
			watchNumTextView = (TextView) v.findViewById(R.id.question_watch_num);
			abstractTextView = (TextView) v.findViewById(R.id.question_abstract);
			timeTextView = (TextView) v.findViewById(R.id.question_time);
		}

	}

}
