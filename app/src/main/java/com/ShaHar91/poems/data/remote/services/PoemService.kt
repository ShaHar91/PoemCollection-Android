package com.shahar91.poems.data.remote.services

import be.appwise.networking.base.BaseService
import com.shahar91.poems.data.remote.models.PoemDto
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PoemService: BaseService {
    @GET("poems")
    fun fetchPoemsForCategoryId(@Query("categories") categoryId: String): Call<List<PoemDto>>

    @GET("poems/{id}")
    fun fetchPoemById(
        @Path("id") poemId: String,
        @Query("userId") userId: String?
    ): Call<PoemDto>

    @FormUrlEncoded
    @POST("poems")
    fun createPoem(
        @Field("title") poemTitle: String,
        @Field("body") poemBody: String,
        @Field("categories") categoryList: List<String>
    ): Call<PoemDto>
}