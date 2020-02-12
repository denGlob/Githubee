package com.denshiksmle.githubee.presentation.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.denshiksmle.githubee.presentation.entities.Event;

public class BaseViewModel extends AndroidViewModel {

    private LiveData<Event<Exception>> exceptions;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    private void init() {
        exceptions = new MediatorLiveData<>();
    }
}
