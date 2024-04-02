package com.shahar91.poems.data.remote

import be.appwise.networking.base.BaseRestClient
import com.shahar91.poems.BuildConfig
import com.shahar91.poems.data.remote.services.AuthService
import com.shahar91.poems.data.remote.services.CategoryService
import com.shahar91.poems.data.remote.services.PoemService
import com.shahar91.poems.data.remote.services.ReviewService

object UnProtectedRestClient : BaseRestClient() {
    override val protectedClient = false
    override fun getBaseUrl() = BuildConfig.API_HOST

    val authService = getService<AuthService>()
    val poemService = getService<PoemService>()
    val categoryService = getService<CategoryService>()
    val reviewService = getService<ReviewService>()
}