package com.example.ivan.privatnews.Presenter;

import android.app.Application;
import android.util.Log;


import com.example.ivan.privatnews.DependeciesInjection.AppComponent;
import com.example.ivan.privatnews.DependeciesInjection.DaggerAppComponent;
import com.example.ivan.privatnews.DependeciesInjection.RESTComponent;
import com.example.ivan.privatnews.DependeciesInjection.RESTModule;


import javax.inject.Inject;


/**
 * Created by misha on 31.10.2016.
 */

public class App extends Application {
    private static AppComponent component;
    private static RESTComponent restComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    public static AppComponent getComponent() {
        return component;
    }



    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }

    public static RESTComponent plusRESTComponent() {
        if (restComponent == null) {
            restComponent = component.plusRESTComponent(new RESTModule());
        }
        return restComponent;
    }

    public static void clearRESTComponent() {
        restComponent = null;
    }
}
