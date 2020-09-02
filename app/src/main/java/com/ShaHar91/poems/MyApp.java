package com.shahar91.poems;

import android.app.Application;

import com.shahar91.poems.data.InitialRealmData;
import com.shahar91.poems.injection.ApplicationComponent;
import com.shahar91.poems.injection.DaggerApplicationComponent;
import com.shahar91.poems.injection.module.ApplicationModule;
import com.shahar91.poems.networking.NewApiManagerService;

import be.appwise.core.core.CoreApp;
import be.appwise.core.extensions.logging.LoggingExtensionsKt;
import be.appwise.core.networking.NetworkingBuilder;
import io.reactivex.plugins.RxJavaPlugins;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApp extends Application {
    private static MyApp instance;
    private ApplicationComponent appComponent;

    public static MyApp getInstance() {
        return instance;
    }

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

        NetworkingBuilder networkBuilder = new NetworkingBuilder()
                .setEndPoint(BuildConfig.API_HOST)
                .setPackageName(getPackageName())
                .setClientSecretValue("")
                .setClientIdValue("")
                .setAppName(getString(R.string.app_name))
                .setVersionName(BuildConfig.VERSION_NAME)
                .setVersionCode(String.valueOf(BuildConfig.VERSION_CODE))
                .setApiVersion("1")
                .setApplicationId(BuildConfig.APPLICATION_ID);

        CoreApp
                .init(this)
                .initializeHawk()
                .initializeLogger(getString(R.string.app_name), BuildConfig.DEBUG)
                .initializeNetworking(networkBuilder, NewApiManagerService.class)
                .initializeErrorActivity(BuildConfig.DEBUG || BuildConfig.FLAVOR == "dev" || BuildConfig.FLAVOR == "stg");

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .initialData(new InitialRealmData())
                .build();
        Realm.setDefaultConfiguration(config);

        // Catch Unhandled RxJava Exceptions
        // https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling
        RxJavaPlugins.setErrorHandler(throwable -> {
            LoggingExtensionsKt.loge(null, throwable, "");

            if (BuildConfig.DEBUG) {
                // Crash the app in debug mode
                throw new Exception(throwable);
            }
        });
    }
}
