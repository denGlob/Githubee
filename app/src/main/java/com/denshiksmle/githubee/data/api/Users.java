package com.denshiksmle.githubee.data.api;

import com.denshiksmle.githubee.domain.entities.DetailUser;
import com.denshiksmle.githubee.domain.entities.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Users {

    @Headers({"User-Agent: Githubee"})
    @GET("users")
    Observable<List<User>> getUsers(@Query("since") int since);

    @Headers({"User-Agent: Githubee"})
    @GET("/users/{username}")
    Observable<DetailUser> getDetailUser(@Path("username") String userName);
}
