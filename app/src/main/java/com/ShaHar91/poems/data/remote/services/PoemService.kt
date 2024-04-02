package com.shahar91.poems.data.remote.services

import be.appwise.networking.base.BaseService
import com.shahar91.poems.data.remote.NetworkResponse
import com.shahar91.poems.data.remote.models.PoemResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PoemService: BaseService {
    @GET("poems")
    fun getPoemsForCategoryId(@Query("categories") categoryId: String): Call<NetworkResponse<List<PoemResponse>>>

    @GET("poems/{id}")
    fun getPoemById(
        @Path("id") poemId: String,
        @Query("userId") userId: String?
    ): Call<NetworkResponse<PoemResponse>>

    @FormUrlEncoded
    @POST("poems")
    fun createPoem(
        @Field("title") poemTitle: String,
        @Field("body") poemBody: String,
        @Field("categories") categoryList: List<String>
    ): Call<NetworkResponse<PoemResponse>>
}