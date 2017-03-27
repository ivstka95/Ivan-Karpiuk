package com.example.ivan.privatnews.Presenter;


import com.example.ivan.privatnews.Model.News;
import com.example.ivan.privatnews.Model.api.EspnAPI;
import com.example.ivan.privatnews.View.NewsActivityView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ivan on 25.03.2017.
 */

public class NewsPresenter {
    private NewsActivityView newsActivityView;
    private Subscription newsSubscription;
    @Inject
    EspnAPI espnAPI;

    public NewsPresenter() {
        App.getComponent().injectEspnAPI(this);
    }

    public void bind(NewsActivityView newsActivityView) {
        this.newsActivityView = newsActivityView;
    }

    public void unSubscribe() {
        newsSubscription.unsubscribe();
    }

    public void getNews() {
        Observable<News> newsObservable = espnAPI.getData("top", "22c28b283d564b0bae082dbb158e045f");

        newsSubscription = newsObservable.map(news -> news.getArticles())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articles -> newsActivityView.showNews(articles));
    }
}
