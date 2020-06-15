package com.shahar91.poems.networking

import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.models.Poem
import retrofit2.Call
import retrofit2.http.GET

interface NewApiManagerService {
    @GET("poems")
    fun getPoems(): Call<NetworkResponse<Poem>>

    @GET("categories")
    fun getCategories(): Call<NetworkResponse<Category>>
}