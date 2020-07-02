package com.shahar91.poems.networking

import be.appwise.core.networking.models.AccessToken
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.models.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NewApiManagerService {
    @GET("poems")
    fun getPoems(@Query("categories") categoryId: String): Call<NetworkResponse<JsonArray>>

    @GET("poems/{id}")
    fun getPoemById(
        @Path("id") poemId: String,
        @Query("userId") userId: String?): Call<NetworkResponse<Poem>>

    @GET("categories")
    fun getCategories(): Call<NetworkResponse<JsonArray>>

    // TODO: this will be used to retrieve the complete list (paged) of reviews for a poem
    @GET("reviews")
    fun getReviewsByPoemId(
        @Query("poem") poemId: String,
        @Query("limit") limit: Int): Call<NetworkResponse<JsonArray>>

    @GET("reviews")
    fun getOwnReviewForPoem(
        @Query("poem") poemId: String,
        @Query("user") userId: String?): Call<NetworkResponse<JsonArray>>

    @FormUrlEncoded
    @POST("auth/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String): Call<AccessToken>

    @GET("auth/me")
    fun getCurrentUser(): Call<NetworkResponse<JsonObject>>
}