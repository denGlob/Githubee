package com.denshiksmle.githubee.presentation.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.denshiksmle.githubee.domain.entities.User;
import com.denshiksmle.githubee.domain.repositories.UserRepository;
import com.denshiksmle.githubee.presentation.entities.Event;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class UsersViewModel extends BaseViewModel {

    private MutableLiveData<Event<List<User>>> updateUsers;

    private UserRepository userRepository;

    public UsersViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Event<List<User>>> getFirstUsers() {
        return getUsers(0);
    }

    public LiveData<Event<List<User>>> getUsers(final int offset) {
        initLiveData();
        userRepository.retrieveUsersOffset(offset)
                .map(users -> new Event(users))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> updateUsers.setValue(result), error -> onProcessException(error));
        return updateUsers;
    }

    private void initLiveData() {
        if (updateUsers == null) {
            updateUsers = new MutableLiveData<>();
        }
    }
}
