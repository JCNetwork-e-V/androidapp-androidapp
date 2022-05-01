package com.jcnetwork.android.app1.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    // Variables
    private static Retrofit mRetrofit = null;

    public static Retrofit getmRetrofit(String url) {
        // Make retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit;
    }

}
