package com.shahar91.poems.networking

import be.appwise.core.networking.base.BaseRestClient
import com.shahar91.poems.BuildConfig

object ProtectedRestClient: BaseRestClient<NewApiManagerService>() {
    override val apiService = NewApiManagerService::class.java
    override val protectedClient = true
    override fun getBaseUrl() = BuildConfig.API_HOST

    override fun enableBagelInterceptor() = true
}