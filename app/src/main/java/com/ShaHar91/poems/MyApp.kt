package com.shahar91.poems

import android.app.Application
import be.appwise.core.core.CoreApp
import be.appwise.networking.Networking
import com.shahar91.poems.di.KoinInitializer

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Networking.Builder(this)
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
                BuildConfig.DEBUG || BuildConfig.FLAVOR === "dev" || BuildConfig.FLAVOR === "stg"
            )
            .build()

        KoinInitializer.init(this)
    }
}