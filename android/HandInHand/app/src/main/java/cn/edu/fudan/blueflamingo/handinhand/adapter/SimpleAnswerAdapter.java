package cn.edu.fudan.blueflamingo.handinhand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.R;
import cn.edu.fudan.blueflamingo.handinhand.lib.AppUtility;
import cn.edu.fudan.blueflamingo.handinhand.model.ExAnswer;

/**
 * The type Simple answer adapter.
 */
public class SimpleAnswerAdapter extends RecyclerView.Adapter<SimpleAnswerAdapter.ViewHolder>{

    /**
     * The interface On item click listener.
     */
    public interface OnItemClickListener {
        /**
         * On item click.
         *
         * @param view the view
         * @param position the position
         */
        void onItemClick(View view, int position);
	}

	private OnItemClickListener mOnItemClickListener;

    /**
     * Sets on item click listener.
     *
     * @param mOnItemClickListener the m on item click listener
     */
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
		this.mOnItemClickListener = mOnItemClickListener;
	}

	private List<ExAnswer> answers;

	private Context mContext;

    /**
     * Instantiates a new Simple answer adapter.
     *
     * @param context the context
     * @param answers the answers
     */
    public SimpleAnswerAdapter(Context context, List<ExAnswer> answers) {
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ExAnswer a = answers.get(i);
		viewHolder.usernameTextView.setText(a.getNickname());
		viewHolder.approveNumTextView.setText(String.valueOf(a.getScore1()));
		String content = a.getContent();
		if (content.length() > 30) {
			viewHolder.abstractTextView.setText(content.substring(0, 30) + "...");
		} else {
			viewHolder.abstractTextView.setText(content);
		}
		viewHolder.timeTextView.setText(simpleDateFormat.format(new Date(a.getCreatedTime())));
        viewHolder.headImageView.setImageBitmap(AppUtility.getImage(a.getUserHead(), mContext));

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

    /**
     * The type View holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Username text view.
         */
        public TextView usernameTextView;
        /**
         * The Head image view.
         */
        public ImageView headImageView;
        /**
         * The Approve num text view.
         */
        public TextView approveNumTextView;
        /**
         * The Abstract text view.
         */
        public TextView abstractTextView;
        /**
         * The Time text view.
         */
        public TextView timeTextView;

        /**
         * Instantiates a new View holder.
         *
         * @param v the v
         */
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
