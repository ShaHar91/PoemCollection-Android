package com.shahar91.poems

import android.app.Application
import be.appwise.core.core.CoreApp
import be.appwise.networking.Networking
import com.shahar91.poems.data.database.PoemDatabase
import com.shahar91.poems.data.repositories.AuthRepository
import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.data.repositories.ReviewRepository
import com.shahar91.poems.data.repositories.UserRepository
import com.shahar91.poems.di.KoinInitializer
import com.shahar91.poems.networking.ProtectedRestClient
import com.shahar91.poems.networking.UnProtectedRestClient

class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

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