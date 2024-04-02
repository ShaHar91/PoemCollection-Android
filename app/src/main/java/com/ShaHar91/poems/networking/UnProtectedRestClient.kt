package com.shahar91.poems.networking

import be.appwise.networking.base.BaseRestClient
import com.shahar91.poems.BuildConfig
import com.shahar91.poems.networking.services.AuthService
import com.shahar91.poems.networking.services.CategoryService
import com.shahar91.poems.networking.services.PoemService
import com.shahar91.poems.networking.services.ReviewService

object UnProtectedRestClient : BaseRestClient() {
    override val protectedClient = false
    override fun getBaseUrl() = BuildConfig.API_HOST

    val authService = getService<AuthService>()
    val poemService = getService<PoemService>()
    val categoryService = getService<CategoryService>()
    val reviewService = getService<ReviewService>()
}