package com.example.ivan.privatnews.DependeciesInjection;

import com.example.ivan.privatnews.Presenter.CurrencyExchangePresenter;
import com.example.ivan.privatnews.Presenter.NewsPresenter;

import dagger.Subcomponent;

/**
 * Created by Ivan on 30.03.2017.
 */
@Subcomponent(modules = {RESTModule.class})
@RESTScope
public interface RESTComponent {

    void injectPrivatAPI(CurrencyExchangePresenter currencyExchangePresenter);

    void injectEspnAPI(NewsPresenter newsPresenter);
}
