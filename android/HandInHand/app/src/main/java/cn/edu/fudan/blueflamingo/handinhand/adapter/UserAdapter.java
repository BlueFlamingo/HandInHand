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
import cn.edu.fudan.blueflamingo.handinhand.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
		this.mOnItemClickListener = mOnItemClickListener;
	}

	private List<User> users;

	private Context mContext;

	public UserAdapter(Context context, List<User> users) {
		this.mContext = context;
		this.users = users;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_item, viewGroup, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
		User u = users.get(i);
		// TODO:设置绑定
		// viewHolder.nicknameTextView.setText(u.getNickname());

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
		return users == null ? 0 : users.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView nicknameTextView;
		public ImageView portraitImageView;
		public TextView signatureTextView;

		public ViewHolder(View v) {
			super(v);
			nicknameTextView = (TextView) v.findViewById(R.id.user_item_nickname);
			portraitImageView = (ImageView) v.findViewById(R.id.user_item_portrait);
			signatureTextView = (TextView) v.findViewById(R.id.user_item_signature);
		}
	}

}
