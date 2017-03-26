package com.example.ivan.privatnews.Presenter;

import android.util.Log;

import com.example.ivan.privatnews.Model.CurrencyExchange;
import com.example.ivan.privatnews.Model.News;
import com.example.ivan.privatnews.View.NewsActivityView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ivan on 25.03.2017.
 */

public class NewsPresenter {
    NewsActivityView newsActivityView;

    public NewsPresenter(NewsActivityView newsActivityView) {
        this.newsActivityView = newsActivityView;
    }

    public void getNews() {
        Observable<News> rates = App.getEspnAPI().getData("top", "22c28b283d564b0bae082dbb158e045f");

        rates.map(news -> news.getArticles())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articles -> newsActivityView.showNews(articles));
    }
}
