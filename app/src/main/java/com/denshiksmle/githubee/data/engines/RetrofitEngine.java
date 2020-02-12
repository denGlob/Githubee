package com.denshiksmle.githubee.data.engines;

import retrofit2.Retrofit;

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

    public Retrofit provideRetorfit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }
}
