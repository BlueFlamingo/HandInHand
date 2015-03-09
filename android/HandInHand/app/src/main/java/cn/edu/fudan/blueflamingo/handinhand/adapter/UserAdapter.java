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
import cn.edu.fudan.blueflamingo.handinhand.lib.AppUtility;
import cn.edu.fudan.blueflamingo.handinhand.model.User;

/**
 * The type User adapter.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

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

	private List<User> users;

	private Context mContext;

    /**
     * Instantiates a new User adapter.
     *
     * @param context the context
     * @param users the users
     */
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
		viewHolder.nicknameTextView.setText(u.getNickname());
		viewHolder.signatureTextView.setText(u.getSignature());
        viewHolder.portraitImageView.setImageBitmap(AppUtility.getImage(u.getPortrait(), mContext));

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

    /**
     * The type View holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Nickname text view.
         */
        public TextView nicknameTextView;
        /**
         * The Portrait image view.
         */
        public ImageView portraitImageView;
        /**
         * The Signature text view.
         */
        public TextView signatureTextView;

        /**
         * Instantiates a new View holder.
         *
         * @param v the v
         */
        public ViewHolder(View v) {
			super(v);
			nicknameTextView = (TextView) v.findViewById(R.id.user_item_nickname);
			portraitImageView = (ImageView) v.findViewById(R.id.user_item_portrait);
			signatureTextView = (TextView) v.findViewById(R.id.user_item_signature);
		}
	}

}
