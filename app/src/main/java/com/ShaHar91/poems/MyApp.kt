package com.shahar91.poems

import android.app.Application
import be.appwise.core.core.CoreApp.init
import be.appwise.core.extensions.logging.loge
import be.appwise.core.networking.NetworkingBuilder
import com.shahar91.poems.data.InitialRealmData
import com.shahar91.poems.networking.NewApiManagerService
import io.reactivex.plugins.RxJavaPlugins
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApp : Application() {
    companion object {
        var instance: MyApp? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val networkBuilder = NetworkingBuilder()
            .setEndPoint(BuildConfig.API_HOST)
            .setPackageName(packageName)
            .setClientSecretValue("")
            .setClientIdValue("")
            .setAppName(getString(R.string.app_name))
            .setVersionName(BuildConfig.VERSION_NAME)
            .setVersionCode(BuildConfig.VERSION_CODE.toString())
            .setApiVersion("1")
            .setApplicationId(BuildConfig.APPLICATION_ID)

        init(this)
            .initializeHawk()
            .initializeLogger(getString(R.string.app_name), BuildConfig.DEBUG)
            .initializeNetworking(networkBuilder, NewApiManagerService::class.java)
            .initializeErrorActivity(
                BuildConfig.DEBUG || BuildConfig.FLAVOR === "dev" || BuildConfig.FLAVOR === "stg")

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .initialData(InitialRealmData())
            .build()
        Realm.setDefaultConfiguration(config)

        // Catch Unhandled RxJava Exceptions
        // https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling
        RxJavaPlugins.setErrorHandler { throwable: Throwable? ->
            loge(null, throwable, "")
            if (BuildConfig.DEBUG) {
                // Crash the app in debug mode
                throw Exception(throwable)
            }
        }
    }
}