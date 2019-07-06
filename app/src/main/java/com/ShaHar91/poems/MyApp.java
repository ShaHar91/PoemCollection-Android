package com.shahar91.poems;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;

import com.shahar91.poems.injection.ApplicationComponent;
import com.shahar91.poems.injection.DaggerApplicationComponent;
import com.shahar91.poems.injection.module.ApplicationModule;
import com.shahar91.poems.ui.error.ErrorActivity;

import cat.ereza.customactivityoncrash.config.CaocConfig;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

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

        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }

        // Catch Unhandled RxJava Exceptions
        // https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling
        RxJavaPlugins.setErrorHandler(throwable -> {
            Timber.e(throwable);

            if (BuildConfig.DEBUG) {
                // Crash the app in debug mode
                throw new Exception(throwable);
            }
        });
    }
}
