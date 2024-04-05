package com.shahar91.poems.data.remote

import be.appwise.networking.NetworkConstants
import be.appwise.networking.base.BaseRestClient
import be.appwise.networking.model.AccessToken
import com.shahar91.poems.BuildConfig
import com.shahar91.poems.data.remote.services.AuthService
import com.shahar91.poems.data.remote.services.CategoryService
import com.shahar91.poems.data.remote.services.PoemService
import com.shahar91.poems.data.remote.services.ReviewService

object ProtectedRestClient : BaseRestClient() {
    override val protectedClient = true
    override fun getBaseUrl() = BuildConfig.API_HOST

    val authService = getService<AuthService>()
    val categoryService = getService<CategoryService>()
    val poemService = getService<PoemService>()
    val reviewService = getService<ReviewService>()

    override fun onRefreshToken(refreshToken: String): AccessToken? {
        return UnProtectedRestClient.authService.refreshToken(
            NetworkConstants.FIELD_IDENTIFIER_REFRESH_TOKEN,
            clientId,
            clientSecret,
            refreshToken
        ).execute().body()
    }
}