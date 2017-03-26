package com.example.ivan.privatnews.Model.api;

import com.example.ivan.privatnews.Model.CurrencyExchange;
import com.example.ivan.privatnews.Model.News;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ivan on 25.03.2017.
 */

public interface EspnAPI {

    @GET("articles?source=espn")
    Observable<News> getData(@Query("sortBy") String sortBy, @Query("apiKey") String apiKey);
}
