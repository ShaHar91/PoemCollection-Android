package com.shahar91.poems.networking

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.models.Review
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewApiManagerService {
    @GET("poems")
    fun getPoems(@Query("categories") categoryId: String): Call<NetworkResponse<JsonArray>>

    @GET("poems/{id}")
    fun getPoemById(@Path("id") poemId: String, @Query("userId") userId: String): Call<NetworkResponse<Poem>>

    @GET("categories")
    fun getCategories(): Call<NetworkResponse<JsonArray>>

    // TODO: this will be used to retrieve the complete list (paged) of reviews for a poem
    @GET("reviews")
    fun getReviewsByPoemId(@Query("poem") poemId: String, @Query("limit") limit: Int): Call<NetworkResponse<JsonArray>>

    @GET("reviews")
    fun getOwnReviewForPoem(@Query("poem") poemId: String, @Query("user") userId: String): Call<NetworkResponse<JsonArray>>
}