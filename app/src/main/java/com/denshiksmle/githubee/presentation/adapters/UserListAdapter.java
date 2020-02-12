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

    public void addItems(List<UserItem> userItems) {
        this.userItems.addAll(userItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserItemHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemHolder holder, int position) {
        holder.bind(userItems.get(position));
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }

    class UserItemHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView userName;

        UserItemHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            userName = itemView.findViewById(R.id.userName);
        }

        void bind(UserItem userItem) {
            Glide.with(itemView.getContext())
                    .load(userItem.getAvatarUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(avatar);

            userName.setText(userItem.getUserName());
        }
    }
}
