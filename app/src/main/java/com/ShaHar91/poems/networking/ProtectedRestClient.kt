package com.shahar91.poems.networking

import be.appwise.core.networking.NetworkConstants
import be.appwise.core.networking.base.BaseRestClient
import be.appwise.core.networking.model.AccessToken
import com.shahar91.poems.BuildConfig

object ProtectedRestClient : BaseRestClient<NewApiManagerService>() {
    override val apiService = NewApiManagerService::class.java
    override val protectedClient = true
    override fun getBaseUrl() = BuildConfig.API_HOST

    override fun enableBagelInterceptor() = true

    override fun onRefreshToken(refreshToken: String): AccessToken? {
        return UnProtectedRestClient.getService.refreshToken(
            NetworkConstants.FIELD_IDENTIFIER_REFRESH_TOKEN,
            clientId,
            clientSecret,
            refreshToken
        ).execute().body()
    }
}