package com.denshiksmle.githubee.presentation.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.denshiksmle.githubee.data.repositories.UserRepositoryImpl;
import com.denshiksmle.githubee.domain.entities.DetailUser;
import com.denshiksmle.githubee.domain.repositories.UserRepository;
import com.denshiksmle.githubee.presentation.entities.Event;
import com.denshiksmle.githubee.presentation.entities.Mappers;
import com.denshiksmle.githubee.presentation.entities.UserDetailInfo;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class DetailUserViewModel extends BaseViewModel {

    private boolean isLoading;
    private MutableLiveData<Event<UserDetailInfo>> detailUserData;
    private UserRepository userRepository;

    public DetailUserViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepositoryImpl();
    }

    public void loadUser(final String userName) {
        if (isLoading) {
            return;
        }
        isLoading = true;
        userRepository.retrieveDetailUser(userName)
                .map(user -> new Event(Mappers.fromDetailUser(user)))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    isLoading = false;
                    detailUserData.setValue(event);
                }, error -> {
                    isLoading = false;
                    onProcessException(error);
                });
    }

    public LiveData<Event<UserDetailInfo>> getUser() {
        if (detailUserData == null) {
            detailUserData = new MutableLiveData<>();
        }
        return detailUserData;
    }
}
