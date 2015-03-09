package cn.edu.fudan.blueflamingo.handinhand.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import cn.edu.fudan.blueflamingo.handinhand.model.ExQuestion;

/**
 * The type Question adapter.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

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

	private List<ExQuestion> questions;

	private Context mContext;

    /**
     * Instantiates a new Question adapter.
     *
     * @param context the context
     * @param questions the questions
     */
    public QuestionAdapter(Context context, List<ExQuestion> questions) {
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		ExQuestion q = questions.get(i);
		viewHolder.titleTextView.setText(q.getTitle());
		viewHolder.watchNumTextView.setText(String.valueOf(q.getScore1()));
		if (q.getContent().length() > 30) {
			viewHolder.abstractTextView.setText(q.getContent().substring(0, 30));
		} else {
			viewHolder.abstractTextView.setText(q.getContent());
		}
		viewHolder.timeTextView.setText(simpleDateFormat.format(new Date(q.getCreatedTime())));
        viewHolder.headImageView.setImageBitmap(AppUtility.getImage(q.getUserHead(), mContext));
        if (q.expireTime != 0) {
			Log.d("emergent question", String.valueOf(q.expireTime));
			Date now = new Date();
			Date eTime = new Date(q.expireTime);
			if (eTime.after(now)) {
				SimpleDateFormat min = new SimpleDateFormat("mm");
				viewHolder.subTitleTextView.setText("还剩下"
						+ min.format(q.expireTime - now.getTime())
						+ "分钟");
			} else {
				viewHolder.subTitleTextView.setText("已过期");
			}
		} else {
			viewHolder.subTitleTextView.setVisibility(View.GONE);
		}

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

    /**
     * The type View holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Title text view.
         */
        public TextView titleTextView;
        /**
         * The Head image view.
         */
        public ImageView headImageView;
        /**
         * The Watch num text view.
         */
        public TextView watchNumTextView;
        /**
         * The Abstract text view.
         */
        public TextView abstractTextView;
        /**
         * The Time text view.
         */
        public TextView timeTextView;
        /**
         * The Sub title text view.
         */
        public TextView subTitleTextView;

        /**
         * Instantiates a new View holder.
         *
         * @param v the v
         */
        public ViewHolder(View v) {
			super(v);
			subTitleTextView = (TextView) v.findViewById(R.id.question_subtitle);
			titleTextView = (TextView) v.findViewById(R.id.question_title);
			headImageView = (ImageView) v.findViewById(R.id.question_user_head);
			watchNumTextView = (TextView) v.findViewById(R.id.question_watch_num);
			abstractTextView = (TextView) v.findViewById(R.id.question_abstract);
			timeTextView = (TextView) v.findViewById(R.id.question_time);
		}

	}

}
