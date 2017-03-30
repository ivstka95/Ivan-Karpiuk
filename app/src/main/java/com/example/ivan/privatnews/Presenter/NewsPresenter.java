package com.example.ivan.privatnews.Presenter;


import com.arellomobile.mvp.InjectViewState;
import com.example.ivan.privatnews.Model.News;
import com.example.ivan.privatnews.Model.api.EspnAPI;
import com.example.ivan.privatnews.View.Views.NewsActivityView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ivan on 25.03.2017.
 */
@InjectViewState

public class NewsPresenter extends BasePresenter<NewsActivityView> {
    @Inject
    EspnAPI espnAPI;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setUpUi();
        getData();
    }

    public NewsPresenter() {
        App.plusRESTComponent().injectEspnAPI(this);
    }

    @Override
    public void getData() {
        Observable<News> newsObservable = espnAPI.getData("top", "22c28b283d564b0bae082dbb158e045f");
        Subscription subscription = newsObservable.map(news -> news.getArticles())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(articles -> getViewState().showData(articles));
        unsubscribeOnDestroy(subscription);
    }
}
