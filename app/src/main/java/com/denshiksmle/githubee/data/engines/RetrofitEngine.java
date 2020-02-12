package com.denshiksmle.githubee.data.engines;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitEngine {

    private static final String BASE_URL = "https://api.github.com/";

    private static final class Holder {
        private static final RetrofitEngine retrofitEngine = new RetrofitEngine();
    }

    public static RetrofitEngine getInstance() {
        return Holder.retrofitEngine;
    }

    private RetrofitEngine() {}

    private Retrofit retrofit;

    public <T> T provideService(Class<T> clazz) {
        return provideRetrofit().create(clazz);
    }

    private Retrofit provideRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(provideOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }
}
