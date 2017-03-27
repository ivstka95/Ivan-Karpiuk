package com.example.ivan.privatnews.DependeciesInjection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.ivan.privatnews.Model.News;
import com.example.ivan.privatnews.Presenter.CurrencyExchangePresenter;
import com.example.ivan.privatnews.Presenter.NewsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ivan on 18.03.2017.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    public CurrencyExchangePresenter provideCurrencyExchangePresenter(){
        return new CurrencyExchangePresenter();
    }

    @Provides
    @Singleton
    public NewsPresenter provideNewsPresenter(){
        return new NewsPresenter();
    }
}
