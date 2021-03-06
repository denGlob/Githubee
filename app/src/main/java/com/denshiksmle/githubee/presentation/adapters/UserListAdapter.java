package com.denshiksmle.githubee.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.denshiksmle.githubee.R;
import com.denshiksmle.githubee.presentation.entities.UserItem;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserItemHolder> {

    private List<UserItem> userItems = new ArrayList<>();
    private OnUserClickListener onUserClickListener;

    public UserListAdapter(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }

    public void addItems(List<UserItem> userItems) {
        if (this.userItems.size() == 0) {
            this.userItems = userItems;
            notifyDataSetChanged();
            return;
        }
        final int positonStart  = this.userItems.size() + 1;
        this.userItems.addAll(userItems);
        notifyItemRangeInserted(positonStart, userItems.size());
    }

    public void clear() {
        userItems.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserItemHolder(row, onUserClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemHolder holder, int position) {
        holder.bind(userItems.get(position));
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }

    static class UserItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnUserClickListener onUserClickListener;
        ImageView avatar;
        TextView userName;

        UserItemHolder(@NonNull View itemView,
                       @NonNull OnUserClickListener onUserClickListener) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            userName = itemView.findViewById(R.id.userName);
            this.onUserClickListener = onUserClickListener;

            itemView.setOnClickListener(this);
        }

        void bind(UserItem userItem) {
            Glide.with(itemView.getContext())
                    .load(userItem.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(avatar);

            userName.setText(userItem.getUserName());
        }

        @Override
        public void onClick(View v) {
            onUserClickListener.onUserClick(userName.getText().toString());
        }
    }

    public interface OnUserClickListener {
        void onUserClick(@NonNull final String userName);
    }
}
