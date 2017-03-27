package com.example.ivan.privatnews.Presenter;

import android.app.Application;
import android.util.Log;


import com.example.ivan.privatnews.DependeciesInjection.AppComponent;
import com.example.ivan.privatnews.DependeciesInjection.DaggerAppComponent;
import com.example.ivan.privatnews.Model.api.EspnAPI;
import com.example.ivan.privatnews.Model.api.PrivatAPI;

import javax.inject.Inject;



/**
 * Created by misha on 31.10.2016.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
        Log.e(">>>>>>>>>>>>>>>>>", "onCreateApp");
    }

    private static AppComponent component;

    public static AppComponent getComponent() {
        Log.e(">>>>>>>>>>>>>>>>>", "getAppComponentOutput = " + component);
        return component;
    }


    protected AppComponent buildComponent() {
        Log.e(">>>>>>>>>>>>>>>>>", "buildAppComponent");
        return DaggerAppComponent.builder()
                .build();
    }
}
