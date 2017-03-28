package com.example.ivan.privatnews.Presenter;

import com.example.ivan.privatnews.View.BaseView;

import rx.Subscription;

/**
 * Created by Ivan on 28.03.2017.
 */

public class BasePresenter {
    protected BaseView view;
    Subscription subscription;
    public void bind(BaseView baseView){
        view = baseView;
    }
    public void unBind(){
        view = null;
    }
    public void getData(){

    }

    public void unSubscribe(){
        subscription.unsubscribe();
    }
}
