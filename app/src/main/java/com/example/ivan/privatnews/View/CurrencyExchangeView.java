package com.example.ivan.privatnews.View;

import com.example.ivan.privatnews.Model.ExchangeRate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 25.03.2017.
 */

public interface CurrencyExchangeView {
    public void showExchangeRate(ExchangeRate exchangeRates);

    public void setToolBarTitle(String toolBarTitle);
}
