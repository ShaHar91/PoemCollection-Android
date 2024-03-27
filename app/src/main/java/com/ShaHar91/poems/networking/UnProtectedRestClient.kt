package com.shahar91.poems.networking

import be.appwise.networking.base.BaseRestClient
import com.shahar91.poems.BuildConfig

object UnProtectedRestClient : BaseRestClient() {
    override val protectedClient = false
    override fun getBaseUrl() = BuildConfig.API_HOST

    override fun enableBagelInterceptor() = true

    val getService: NewApiManagerService by lazy {
        ProtectedRestClient.getRetrofit.create(NewApiManagerService::class.java)
    }
}