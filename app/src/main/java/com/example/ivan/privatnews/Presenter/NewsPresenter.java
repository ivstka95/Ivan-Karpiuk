package com.example.ivan.privatnews.Presenter;


import com.example.ivan.privatnews.Model.News;
import com.example.ivan.privatnews.Model.api.EspnAPI;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ivan on 25.03.2017.
 */

public class NewsPresenter extends BasePresenter {
    @Inject
    EspnAPI espnAPI;

    public NewsPresenter() {
        App.getComponent().injectEspnAPI(this);
    }

    @Override
    public void getData() {
        Observable<News> newsObservable = espnAPI.getData("top", "22c28b283d564b0bae082dbb158e045f");
        subscription = newsObservable.map(news -> news.getArticles())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articles -> view.showData(articles));
    }
}
