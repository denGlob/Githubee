package com.denshiksmle.githubee.presentation.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denshiksmle.githubee.data.repositories.UserRepositoryImpl;
import com.denshiksmle.githubee.domain.repositories.UserRepository;
import com.denshiksmle.githubee.presentation.entities.Event;
import com.denshiksmle.githubee.presentation.entities.Mappers;
import com.denshiksmle.githubee.presentation.entities.UserItem;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class UsersViewModel extends BaseViewModel {

    private boolean isLoading;
    private RecyclerView.OnScrollListener onScrollListener;
    private MutableLiveData<Event<List<UserItem>>> updateUsers;
    private MutableLiveData<Event<Boolean>> clearUsers;

    private UserRepository userRepository;

    public UsersViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepositoryImpl();
    }

    public LiveData<Event<List<UserItem>>> getUsers() {
        if (updateUsers == null) {
            updateUsers = new MutableLiveData<>();
        }
        return updateUsers;
    }

    public LiveData<Event<Boolean>> getClearing() {
        if (clearUsers == null) {
            clearUsers = new MutableLiveData<>();
        }
        return clearUsers;
    }

    public void clearUsers() {
        clearUsers.setValue(new Event<>(true));
    }

    public void requestUsers(final int offset) {
        if (isLoading) {
            return;
        }
        isLoading = true;
        Disposable disposable = userRepository.retrieveUsersOffset(offset)
                .map(users -> new Event(Mappers.fromUsers(users)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    isLoading = false;
                    updateUsers.setValue(result);
                }, error -> {
                    isLoading = false;
                    onProcessException(error);
                });
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        if (onScrollListener != null) {
            return onScrollListener;
        }
        onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (isLoading) {
                    return;
                }
                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                final int itemsCount = recyclerView.getAdapter().getItemCount() - 10;
                if(itemsCount == llm.findFirstCompletelyVisibleItemPosition()) {
                    requestUsers(itemsCount + 1);
                }
            }
        };
        return onScrollListener;
    }
}
