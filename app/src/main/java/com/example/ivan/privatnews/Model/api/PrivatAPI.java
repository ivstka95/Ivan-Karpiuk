package com.example.ivan.privatnews.Model.api;

import com.example.ivan.privatnews.Model.CurrencyExchange;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by misha on 31.10.2016.
 */
public interface PrivatAPI {

    @GET("exchange_rates?json")
    Observable<CurrencyExchange> getData(@Query("date") String date);

}
