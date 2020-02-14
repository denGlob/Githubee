package com.denshiksmle.githubee.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.denshiksmle.githubee.R;
import com.denshiksmle.githubee.presentation.entities.UserDetailInfo;
import com.denshiksmle.githubee.presentation.viewmodels.DetailUserViewModel;

public class DetailUserFragment extends BaseFragment {

    private ImageView avatarView;
    private TextView userNameView;
    private TextView userUrlView;
    private TextView reposView;
    private TextView gistsView;
    private TextView followersView;

    private DetailUserViewModel detailUserViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.detail_user_fragment, container, false);

        avatarView = rootView.findViewById(R.id.avatar);
        userNameView = rootView.findViewById(R.id.userName);
        userUrlView = rootView.findViewById(R.id.userUrl);
        reposView = rootView.findViewById(R.id.repos);
        gistsView = rootView.findViewById(R.id.gists);
        followersView = rootView.findViewById(R.id.followers);

        detailUserViewModel = new ViewModelProvider(this).get(DetailUserViewModel.class);

        final String userName = getArguments().getString("UserNameKey", null);
        detailUserViewModel.loadUser(userName);
        detailUserViewModel.getUser().observe(getViewLifecycleOwner(), userEvent -> {
            if (userEvent.isHandled()) {
                return;
            }
            showUser(userEvent.getHandleContent());
        });

        return rootView;
    }

    private void showUser(final UserDetailInfo detailInfo) {
        Glide.with(getContext())
                .load(detailInfo.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(avatarView);

        userNameView.setText(detailInfo.getUserName());
        userUrlView.setText(detailInfo.getUserUrl());

        final String repos = getString(R.string.repos_item) + detailInfo.getUserRepos();
        final String gists = getString(R.string.gists_item) + detailInfo.getUserGists();
        final String followers = getString(R.string.followers_item) + detailInfo.getFollowers();

        reposView.setText(repos);
        gistsView.setText(gists);
        followersView.setText(followers);

    }
}
