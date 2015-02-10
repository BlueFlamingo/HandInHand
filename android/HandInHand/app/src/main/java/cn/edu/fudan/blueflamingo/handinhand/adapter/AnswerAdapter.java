package cn.edu.fudan.blueflamingo.handinhand.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.edu.fudan.blueflamingo.handinhand.R;
import cn.edu.fudan.blueflamingo.handinhand.middleware.FavoriteHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.AnswerHeader;
import cn.edu.fudan.blueflamingo.handinhand.model.ExAnswer;

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
		this.mOnItemClickListener = mOnItemClickListener;
	}

	private List<ExAnswer> answers;
	private AnswerHeader answerHeader;
	private FavoriteHelper favoriteHelper;

	private Context mContext;

	private static final int TYPE_HEADER = 0;
	private static final int TYPE_ITEM = 1;

	public AnswerAdapter(Context context, List<ExAnswer> answers, AnswerHeader answerHeader) {
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
			ButtonFlat btn_favor = (ButtonFlat) v.findViewById(R.id.question_item_btn_watch);
			btn_favor.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					(new WatchQuestionTask()).execute();
				}
			});
			return new VHHeader(v);
		}
		throw new RuntimeException("There is no type that matches the type");
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (viewHolder instanceof VHItem) {
			ExAnswer a = answers.get(i - 1);    //由于header占了第0个位置
			//TODO:设置头像
			final VHItem item = (VHItem) viewHolder;
			item.usernameTextView.setText(a.getNickname());
			item.timeTextView.setText(simpleDateFormat.format(new Date(a.getCreatedTime())));
			String abstractContent = a.getContent();
			if (abstractContent.length() > 30) {
				item.abstractTextView.setText(abstractContent.substring(0, 30) + "...");
			} else {
				item.abstractTextView.setText(a.getContent());
			}
			item.approveNumTextView.setText(String.valueOf(a.getScore1()));


			//如果设置了回调则设置点击事件
			if (mOnItemClickListener != null) {
				item.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mOnItemClickListener.onItemClick(item.itemView, i);
					}
				});
			}

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

	private class WatchQuestionTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			favoriteHelper = new FavoriteHelper();
			SharedPreferences mPrefs = mContext.getSharedPreferences("handinhand", Activity.MODE_PRIVATE);
			int uid = mPrefs.getInt("uid", -1);
			if (uid == -1) {
				return -1;
			}
			int qid = answerHeader.getQid();
			return favoriteHelper.switchQuestionFavorite(uid, qid);
		}

		@Override
		protected void onPostExecute(Integer res) {
			switch (res) {
				case 1:
					answerHeader.setWatchNum(answerHeader.getWatchNum() + 1);
					Toast.makeText(mContext, "成功关注此问题", Toast.LENGTH_SHORT).show();
					break;
				case 0:
					answerHeader.setWatchNum(answerHeader.getWatchNum() - 1);
					Toast.makeText(mContext, "取消关注此问题", Toast.LENGTH_SHORT).show();
					break;
				case -1:
					Toast.makeText(mContext, "请先登录后再操作", Toast.LENGTH_SHORT).show();
					break;
			}
			notifyDataSetChanged();
		}
	}
}
