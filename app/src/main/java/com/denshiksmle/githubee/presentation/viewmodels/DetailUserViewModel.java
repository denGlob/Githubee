package com.denshiksmle.githubee.presentation.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.denshiksmle.githubee.domain.entities.DetailUser;
import com.denshiksmle.githubee.domain.repositories.UserRepository;
import com.denshiksmle.githubee.presentation.entities.Event;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class DetailUserViewModel extends BaseViewModel {

    private MutableLiveData<Event<DetailUser>> detailUserData;
    private UserRepository userRepository;

    public DetailUserViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Event<DetailUser>> getUser(@NonNull final String userName) {
        initLiveData();
        userRepository.retrieveDetailUser(userName)
                .map(user -> new Event(user))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> detailUserData.setValue(event), error -> onProcessException(error));
        return detailUserData;
    }

    private void initLiveData() {
        if (detailUserData == null) {
            detailUserData = new MutableLiveData<>();
        }
    }
}
