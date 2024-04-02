package com.shahar91.poems.data.remote.services

import be.appwise.networking.base.BaseService
import com.shahar91.poems.data.remote.NetworkResponse
import com.shahar91.poems.data.remote.models.ReviewResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService: BaseService {

    // TODO: this will be used to retrieve the complete list (paged) of reviews for a poem
    @GET("reviews")
    fun getReviewsByPoemId(
        @Query("poem") poemId: String/*,
        @Query("limit") limit: Int*/
    ): Call<NetworkResponse<List<ReviewResponse>>>

    @GET("reviews")
    fun fetchOwnReviewForPoem(
        @Query("poem") poemId: String,
        @Query("user") userId: String?
    ): Call<NetworkResponse<List<ReviewResponse>>>

    @FormUrlEncoded
    @POST("poems/{id}/reviews")
    fun createReview(
        @Path("id") poemId: String,
        @Field("text") reviewText: String,
        @Field("rating") reviewRating: Float
    ): Call<NetworkResponse<ReviewResponse>>

    @FormUrlEncoded
    @PUT("reviews/{id}")
    fun editReview(
        @Path("id") reviewId: String,
        @Field("text") reviewText: String,
        @Field("rating") reviewRating: Float
    ): Call<NetworkResponse<ReviewResponse>>

    @DELETE("reviews/{id}")
    fun deleteReview(
        @Path("id") reviewId: String
    ): Call<NetworkResponse<ReviewResponse>>
}