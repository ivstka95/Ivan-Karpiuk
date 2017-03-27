package com.example.ivan.privatnews.DependeciesInjection;

import android.support.annotation.NonNull;

import com.example.ivan.privatnews.Model.api.EspnAPI;
import com.example.ivan.privatnews.Model.api.PrivatAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ivan on 27.03.2017.
 */
@Module
public class RESTModule {

    @Provides
    @NonNull
    @Singleton
    public PrivatAPI providePrivatAPI() {
        Retrofit privatRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.privatbank.ua/p24api/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PrivatAPI privatAPI = privatRetrofit.create(PrivatAPI.class);
        return privatAPI;
    }

    @Provides
    @NonNull
    @Singleton
    public EspnAPI provideEspnAPI() {
        Retrofit espnRetrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v1/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EspnAPI espnAPI = espnRetrofit.create(EspnAPI.class);
        return espnAPI;
    }


}
