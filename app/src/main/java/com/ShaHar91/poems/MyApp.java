package com.shahar91.poems;

import android.app.Application;

import com.shahar91.poems.injection.ApplicationComponent;
import com.shahar91.poems.injection.DaggerApplicationComponent;
import com.shahar91.poems.injection.module.ApplicationModule;
import com.shahar91.poems.ui.error.ErrorActivity;

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
