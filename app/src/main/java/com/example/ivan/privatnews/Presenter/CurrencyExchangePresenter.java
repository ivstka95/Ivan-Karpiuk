package com.example.ivan.privatnews.Presenter;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ivan.privatnews.Model.CurrencyExchange;
import com.example.ivan.privatnews.Model.api.EspnAPI;
import com.example.ivan.privatnews.Model.api.PrivatAPI;
import com.example.ivan.privatnews.R;
import com.example.ivan.privatnews.View.CurrencyExchangeView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static rx.subscriptions.Subscriptions.from;

/**
 * Created by Ivan on 25.03.2017.
 */

public class CurrencyExchangePresenter {
    @Inject
    PrivatAPI privatAPI;
    private String date = "";
    private String currency = "USD";
    private CurrencyExchangeView currencyExchangeView;
    private Subscription ratesSubscription;

    public CurrencyExchangePresenter() {
        App.getComponent().injectPrivatAPI(this);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void bind(CurrencyExchangeView currencyExchangeView) {
        this.currencyExchangeView = currencyExchangeView;
    }

    public void unSubscribe() {
        ratesSubscription.unsubscribe();
    }

    public void getExchangeRates() {
        Observable<CurrencyExchange> rates = privatAPI.getData(date);
        ratesSubscription = rates.map(currencyExchange -> currencyExchange.getExchangeRate())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(exchangeRates -> Observable.from(exchangeRates))
                .filter(exchangeRate -> exchangeRate.getCurrency().equals(currency))
                .subscribe(exchangeRate -> currencyExchangeView.showExchangeRate(exchangeRate));
        currencyExchangeView.setToolBarTitle("Rates for: " + currency + " " + date);
    }

    public void onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.AUD) {
            currency = "AUD";
        }
        if (id == R.id.CAD) {
            currency = "CAD";
        }
        if (id == R.id.CZK) {
            currency = "CZK";
        }
        if (id == R.id.DKK) {
            currency = "DKK";
        }
        if (id == R.id.HUF) {
            currency = "HUF";
        }
        if (id == R.id.ILS) {
            currency = "ILS";
        }
        if (id == R.id.JPY) {
            currency = "JPY";
        }
        if (id == R.id.LVL) {
            currency = "LVL";
        }
        if (id == R.id.LTL) {
            currency = "LTL";
        }
        if (id == R.id.NOK) {
            currency = "NOK";
        }
        if (id == R.id.SKK) {
            currency = "SKK";
        }
        if (id == R.id.SEK) {
            currency = "SEK";
        }
        if (id == R.id.CHF) {
            currency = "CHF";
        }
        if (id == R.id.RUB) {
            currency = "RUB";
        }
        if (id == R.id.GBP) {
            currency = "GBP";
        }
        if (id == R.id.USD) {
            currency = "USD";
        }
        if (id == R.id.BYR) {
            currency = "BYR";
        }
        if (id == R.id.EUR) {
            currency = "EUR";
        }
        if (id == R.id.GEL) {
            currency = "GEL";
        }
        if (id == R.id.PLZ) {
            currency = "PLZ";
        }
        currencyExchangeView.setToolBarTitle("Rates for: " + currency + " " + date);
    }
}
