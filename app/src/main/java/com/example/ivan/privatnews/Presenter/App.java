package com.example.ivan.privatnews.Presenter;

import android.app.Application;


import com.example.ivan.privatnews.Model.api.EspnAPI;
import com.example.ivan.privatnews.Model.api.PrivatAPI;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by misha on 31.10.2016.
 */

public class App extends Application {

    private static PrivatAPI privatAPI;
    private Retrofit privatRetrofit;
    private static EspnAPI espnAPI;
    private Retrofit espnRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        privatRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua/p24api/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        privatAPI = privatRetrofit.create(PrivatAPI.class);

        espnRetrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v1/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        espnAPI = espnRetrofit.create(EspnAPI.class);
    }

    public static PrivatAPI getPrivatAPI() {
        return privatAPI;
    }

    public static EspnAPI getEspnAPI() {
        return espnAPI;
    }
}
