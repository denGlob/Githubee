package com.denshiksmle.githubee.presentation.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.denshiksmle.githubee.R;
import com.denshiksmle.githubee.presentation.adapters.UserListAdapter;
import com.denshiksmle.githubee.presentation.viewmodels.UsersViewModel;

import java.util.HashMap;
import java.util.Map;

public class UsersFragment extends BaseFragment {

    private RecyclerView usersView;
    private SwipeRefreshLayout refreshLayout;
    private UsersViewModel usersViewModel;
    private UserListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new UserListAdapter(this::navigate);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.users_fragment, container, false);

        usersView = rootView.findViewById(R.id.usersView);
        usersView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshLayout = rootView.findViewById(R.id.refreshLayout);

        initViewModel();

        refreshLayout.setOnRefreshListener(() -> {
            usersViewModel.clearUsers();
            usersViewModel.requestUsers(0);
        });

        usersView.addOnScrollListener(usersViewModel.getOnScrollListener());
        usersView.setAdapter(adapter);

        if (adapter.getItemCount() == 0) {
            usersViewModel.requestUsers(0);
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        usersView.removeOnScrollListener(usersViewModel.getOnScrollListener());
    }

    private void navigate(final String selectedUserName) {
        final Bundle bundle = new Bundle();
        bundle.putString("username", selectedUserName);
        NavHostFragment.findNavController(this).navigate(R.id.detailUserFragment, bundle);
    }

    private void initViewModel() {
        usersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        usersViewModel.getUsers().observe(getViewLifecycleOwner(), usersEvent -> {
            if (usersEvent.isHandled()) {
                return;
            }
            adapter.addItems(usersEvent.getHandleContent());
            hideLoading();
        });

        usersViewModel.getClearing().observe(getViewLifecycleOwner(), clearingEvent -> {
            if (clearingEvent.isHandled()) {
                return;
            }
            adapter.clear();
        });

        usersViewModel.getExceptionMessage().observe(getViewLifecycleOwner(), errorMsgEvent -> {
            if (errorMsgEvent.isHandled()) {
                return;
            }
            showSnakbar(errorMsgEvent.getHandleContent());
        });
    }

    private void hideLoading() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }
}
