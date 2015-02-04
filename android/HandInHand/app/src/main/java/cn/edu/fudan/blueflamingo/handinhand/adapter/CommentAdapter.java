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
import cn.edu.fudan.blueflamingo.handinhand.model.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

	private List<Comment> comments;

	private Context mContext;

	public CommentAdapter(Context context, List<Comment> comments) {
		this.mContext = context;
		this.comments = comments;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_item, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int i) {
		Comment c = comments.get(i);
		//TODO:设置具体内容
		//viewHolder.contentTextView.setText(c.getContent());
	}

	@Override
	public int getItemCount() {
		return comments == null ? 0 : comments.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ImageView portraitImageView;
		public TextView usernameTextView;
		public TextView contentTextView;
		public TextView timeTextView;

		public ViewHolder(View v) {
			super(v);
			portraitImageView = (ImageView) v.findViewById(R.id.comment_item_portrait);
			usernameTextView = (TextView) v.findViewById(R.id.comment_item_username);
			contentTextView = (TextView) v.findViewById(R.id.comment_item_content);
			timeTextView = (TextView) v.findViewById(R.id.comment_item_time);
		}
	}
}
