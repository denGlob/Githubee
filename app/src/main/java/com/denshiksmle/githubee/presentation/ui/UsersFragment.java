package com.denshiksmle.githubee.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.denshiksmle.githubee.R;
import com.denshiksmle.githubee.presentation.adapters.UserListAdapter;
import com.denshiksmle.githubee.presentation.viewmodels.UsersViewModel;

public class UsersFragment extends BaseFragment {

    private RecyclerView usersView;
    private SwipeRefreshLayout refreshLayout;
    private UsersViewModel usersViewModel;
    private UserListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.users_fragment, container, false);

        usersView = rootView.findViewById(R.id.usersView);
        usersView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout = rootView.findViewById(R.id.refreshLayout);

        adapter = new UserListAdapter();

        refreshLayout.setOnRefreshListener(() -> {
            usersViewModel.clearUsers();
            usersViewModel.requestUsers(0);
        });

        usersView.addOnScrollListener(usersViewModel.getOnScrollListener());

        initViewModel();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        usersView.removeOnScrollListener(usersViewModel.getOnScrollListener());
    }

    public void initViewModel() {
        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        usersViewModel.getUsers().observe(getViewLifecycleOwner(), usersEvent -> {
            if (usersEvent.isHandled()) {
                return;
            }
            adapter.addItems(usersEvent.getHandleContent());
        });

        usersViewModel.getClearing().observe(getViewLifecycleOwner(), clearingEvent -> {
            if (clearingEvent.isHandled()) {
                return;
            }
            adapter.clear();
        });

        usersViewModel.getExceptionMessage().observe(getViewLifecycleOwner(), errorMsg -> {

        });
    }
}
