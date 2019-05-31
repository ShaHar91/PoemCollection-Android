package com.ShaHar91.poemcollection;

import android.app.Application;

import com.ShaHar91.poemcollection.injection.ApplicationComponent;
import com.ShaHar91.poemcollection.injection.DaggerApplicationComponent;
import com.ShaHar91.poemcollection.injection.module.ApplicationModule;
import com.ShaHar91.poemcollection.ui.error.ErrorActivity;

import cat.ereza.customactivityoncrash.config.CaocConfig;

public class MyApp extends Application {
    private static MyApp instance;
    private ApplicationComponent appComponent;

    public static MyApp getInstance() { return instance; }

    public ApplicationComponent getAppComponent() {
        return this.appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        this.appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        CaocConfig.Builder.create()
                .showErrorDetails(true)
                .errorActivity(ErrorActivity.class)
                .apply();
    }
}
