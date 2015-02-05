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

public class SimpleAnswerAdapter extends RecyclerView.Adapter<SimpleAnswerAdapter.ViewHolder>{

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
		this.mOnItemClickListener = mOnItemClickListener;
	}

	private List<Answer> answers;

	private Context mContext;

	public SimpleAnswerAdapter(Context context, List<Answer> answers) {
		this.mContext = context;
		this.answers = answers;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.answer_item, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
			Answer a = answers.get(i);	//由于header占了第0个位置
			//TODO:设置username、头像、点赞数、时间
			//viewHolder.abstractTextView.setText(a.getContent());

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
		return answers == null ? 0 : answers.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView usernameTextView;
		public ImageView headImageView;
		public TextView approveNumTextView;
		public TextView abstractTextView;
		public TextView timeTextView;

		public ViewHolder(View v) {
			super(v);
			usernameTextView = (TextView) v.findViewById(R.id.answer_user_name);
			headImageView = (ImageView) v.findViewById(R.id.answer_user_head);
			approveNumTextView = (TextView) v.findViewById(R.id.answer_approve_num);
			abstractTextView = (TextView) v.findViewById(R.id.answer_abstract);
			timeTextView = (TextView) v.findViewById(R.id.answer_time);
		}
	}

}
