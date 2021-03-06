package com.denshiksmle.githubee.domain.repositories;

import androidx.annotation.NonNull;

import com.denshiksmle.githubee.domain.entities.DetailUser;
import com.denshiksmle.githubee.domain.entities.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface UserRepository {

    Observable<List<User>> retrieveUsersOffset(int offset);

    Observable<DetailUser> retrieveDetailUser(@NonNull String username);
}
