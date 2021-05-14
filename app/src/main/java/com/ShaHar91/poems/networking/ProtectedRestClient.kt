package com.shahar91.poems.networking

import be.appwise.core.networking.NetworkConstants
import be.appwise.core.networking.base.BaseRestClient
import be.appwise.core.networking.model.AccessToken
import com.shahar91.poems.BuildConfig

object ProtectedRestClient : BaseRestClient() {
    override val protectedClient = true
    override fun getBaseUrl() = BuildConfig.API_HOST

    override fun enableBagelInterceptor() = true

    val getService: NewApiManagerService by lazy {
        getRetrofit.create(NewApiManagerService::class.java)
    }

    override fun onRefreshToken(refreshToken: String): AccessToken? {
        return UnProtectedRestClient.getService.refreshToken(
                NetworkConstants.FIELD_IDENTIFIER_REFRESH_TOKEN,
                clientId,
                clientSecret,
                refreshToken
        ).execute().body()
    }
}