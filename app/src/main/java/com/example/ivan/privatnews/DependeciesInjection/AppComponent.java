package com.example.ivan.privatnews.DependeciesInjection;


import com.example.ivan.privatnews.Presenter.CurrencyExchangePresenter;
import com.example.ivan.privatnews.Presenter.NewsPresenter;
import com.example.ivan.privatnews.View.Activities.CurrencyExchangeActivity;
import com.example.ivan.privatnews.View.Activities.NewsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ivan on 18.03.2017.
 */

@Component(modules = {AppModule.class})
@Singleton

public interface AppComponent {

    void injectCurrencyExchange(CurrencyExchangeActivity currencyExchangeActivity);

    void injectNews(NewsActivity newsActivity);

    RESTComponent plusRESTComponent(RESTModule restModule);

}
