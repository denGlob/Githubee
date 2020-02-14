package com.denshiksmle.githubee.presentation.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.denshiksmle.githubee.presentation.entities.Event;

public class BaseViewModel extends AndroidViewModel {

    private LiveData<Event<String>> exceptionMessage;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    private void init() {
        exceptionMessage = new MediatorLiveData<>();
    }

    protected void onProcessException(@NonNull final Throwable e) {

    }

    public LiveData<Event<String>> getExceptionMessage() {
        return exceptionMessage;
    }
}
