package com.shahar91.poems

import android.app.Application
import be.appwise.core.core.CoreApp
import be.appwise.core.networking.Networking
import com.shahar91.poems.data.InitialRealmData
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

        Networking.Builder()
            .setPackageName(packageName)
            .setAppName(getString(R.string.app_name))
            .setApiVersion("1")
            .setVersionCode(BuildConfig.VERSION_CODE.toString())
            .setVersionName(BuildConfig.VERSION_NAME)
            .setClientIdValue("")
            .setClientSecretValue("")
            .build()

        CoreApp.init(this)
            .initializeLogger(getString(R.string.app_name), BuildConfig.DEBUG)
            .initializeErrorActivity(
                BuildConfig.DEBUG || BuildConfig.FLAVOR === "dev" || BuildConfig.FLAVOR === "stg")
            .build()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .initialData(InitialRealmData())
            .build()
        Realm.setDefaultConfiguration(config)
    }
}