package com.shahar91.poems

import android.app.Application
import be.appwise.core.core.CoreApp
import be.appwise.core.networking.Networking
import com.shahar91.poems.data.database.PoemDatabase
import com.shahar91.poems.data.repositories.AuthRepository
import com.shahar91.poems.data.repositories.CategoryRepository
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.data.repositories.ReviewRepository
import com.shahar91.poems.data.repositories.UserRepository
import com.shahar91.poems.networking.ProtectedRestClient
import com.shahar91.poems.networking.UnProtectedRestClient

class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp
            private set

        private val poemDatabase: PoemDatabase by lazy {
            PoemDatabase.getDatabase(instance.applicationContext)
        }

        val categoryRepository: CategoryRepository by lazy {
            CategoryRepository(poemDatabase.categoryDao(), UnProtectedRestClient.getService)
        }

        val userRepository: UserRepository by lazy {
            UserRepository(poemDatabase.userDao(), ProtectedRestClient.getService)
        }

        val poemRepository: PoemRepository by lazy {
            PoemRepository(poemDatabase.poemDao(), poemDatabase.userDao(), poemDatabase.reviewDao(), poemDatabase.poemCategoryCrossRefDao(), ProtectedRestClient.getService, UnProtectedRestClient.getService)
        }

        val authRepository: AuthRepository by lazy {
            AuthRepository(UnProtectedRestClient.getService, userRepository)
        }

        val reviewRepository: ReviewRepository by lazy {
            ReviewRepository(poemDatabase.reviewDao(), ProtectedRestClient.getService)
        }
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
            .registerBagelService(this)
            .build()

        CoreApp.init(this)
            .initializeLogger(getString(R.string.app_name), BuildConfig.DEBUG)
            .initializeErrorActivity(
                BuildConfig.DEBUG || BuildConfig.FLAVOR === "dev" || BuildConfig.FLAVOR === "stg")
            .build()
    }
}