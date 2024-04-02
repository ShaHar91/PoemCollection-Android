package com.shahar91.poems.data.remote.services

import be.appwise.networking.base.BaseService
import com.shahar91.poems.data.remote.NetworkResponse
import com.shahar91.poems.data.remote.models.CategoryDto
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService: BaseService {
    @GET("categories")
    fun fetchCategories(): Call<NetworkResponse<List<CategoryDto>>>
}