package com.example.ivan.privatnews.DependeciesInjection;


import com.example.ivan.privatnews.Presenter.CurrencyExchangePresenter;
import com.example.ivan.privatnews.Presenter.NewsPresenter;
import com.example.ivan.privatnews.View.CurrencyExchangeActivity;
import com.example.ivan.privatnews.View.NewsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ivan on 18.03.2017.
 */

@Component(modules = {AppModule.class, RESTModule.class})
@Singleton

public interface AppComponent {

    void injectCurrencyExchange(CurrencyExchangeActivity currencyExchangeActivity);
    void injectNews(NewsActivity newsActivity);
    void injectPrivatAPI(CurrencyExchangePresenter currencyExchangePresenter);
    void injectEspnAPI(NewsPresenter newsPresenter);

}
