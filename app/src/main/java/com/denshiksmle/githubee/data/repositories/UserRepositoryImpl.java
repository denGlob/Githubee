package com.denshiksmle.githubee.data.repositories;

import androidx.annotation.NonNull;

import com.denshiksmle.githubee.data.api.Users;
import com.denshiksmle.githubee.data.engines.RetrofitEngine;
import com.denshiksmle.githubee.domain.entities.DetailUser;
import com.denshiksmle.githubee.domain.entities.User;
import com.denshiksmle.githubee.domain.repositories.UserRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class UserRepositoryImpl implements UserRepository {

    private RetrofitEngine retrofitEngine;

    @Override
    public Observable<List<User>> retrieveUsersOffset(int offset) {
        return retrofitEngine.provideService(Users.class)
                .getUsers(offset)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<DetailUser> retrieveDetailUser(@NonNull String username) {
        return retrofitEngine.provideService(Users.class)
                .getDetailUser(username)
                .subscribeOn(Schedulers.io());
    }
}
