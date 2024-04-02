package com.shahar91.poems.networking.services

import be.appwise.networking.base.BaseService
import com.shahar91.poems.networking.NetworkResponse
import com.shahar91.poems.networking.models.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService: BaseService {
    @GET("categories")
    fun getCategories(): Call<NetworkResponse<List<CategoryResponse>>>
}