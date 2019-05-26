package com.ShaHar91.poemcollection;

import android.app.Application;

import com.ShaHar91.poemcollection.ui.error.ErrorActivity;

import cat.ereza.customactivityoncrash.config.CaocConfig;

public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance() { return instance; }
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        CaocConfig.Builder.create()
                .showErrorDetails(true)
                .errorActivity(ErrorActivity.class)
                .apply();
    }
}
