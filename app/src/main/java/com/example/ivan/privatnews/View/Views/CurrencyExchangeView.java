package com.example.ivan.privatnews.View.Views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.ivan.privatnews.Model.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 25.03.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface CurrencyExchangeView extends MvpView {
    void showData(Object object);

    void setToolBarTitle(String title);
}
