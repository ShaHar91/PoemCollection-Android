package com.shahar91.poems.networking

import be.appwise.core.networking.NetworkConstants
import be.appwise.core.networking.model.AccessToken
import com.shahar91.poems.networking.models.CategoryResponse
import com.shahar91.poems.networking.models.PoemResponse
import com.shahar91.poems.networking.models.ReviewResponse
import com.shahar91.poems.networking.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface NewApiManagerService {
    //<editor-fold desc="Auth">
    @FormUrlEncoded
    @POST("auth/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String): Call<AccessToken>

    @FormUrlEncoded
    @POST("auth/register")
    fun registerUser(
        @Field("username") userName: String,
        @Field("email") email: String,
        @Field("password") password: String): Call<AccessToken>

    @FormUrlEncoded
    @POST("auth/token")
    fun refreshToken(
        @Field(NetworkConstants.KEY_GRANT_TYPE) grantType: String,
        @Field(NetworkConstants.KEY_CLIENT_ID) clientId: String,
        @Field(NetworkConstants.KEY_CLIENT_SECRET) client_secret: String,
        @Field(NetworkConstants.FIELD_IDENTIFIER_REFRESH_TOKEN) refreshToken: String
    ): Call<AccessToken>

    @GET("auth/me")
    fun getCurrentUser(): Call<NetworkResponse<UserResponse>>
    //</editor-fold>

    //<editor-fold desc="Categories">
    @GET("categories")
    fun getCategories(): Call<NetworkResponse<List<CategoryResponse>>>
    //</editor-fold>

    //<editor-fold desc="Poems">
    @GET("poems")
    fun getPoemsForCategoryId(@Query("categories") categoryId: String): Call<NetworkResponse<List<PoemResponse>>>

    @GET("poems/{id}")
    fun getPoemById(
        @Path("id") poemId: String,
        @Query("userId") userId: String?): Call<NetworkResponse<PoemResponse>>

    @FormUrlEncoded
    @POST("poems")
    fun createPoem(
        @Field("title") poemTitle: String,
        @Field("body") poemBody: String,
        @Field("categories") categoryList: List<String>): Call<NetworkResponse<PoemResponse>>
    //</editor-fold>

    //<editor-fold desc="Reviews">
    // TODO: this will be used to retrieve the complete list (paged) of reviews for a poem
    @GET("reviews")
    fun getReviewsByPoemId(
        @Query("poem") poemId: String/*,
        @Query("limit") limit: Int*/): Call<NetworkResponse<List<ReviewResponse>>>

    @GET("reviews")
    fun getOwnReviewForPoem(
        @Query("poem") poemId: String,
        @Query("user") userId: String?): Call<NetworkResponse<List<ReviewResponse>>>

    @FormUrlEncoded
    @POST("poems/{id}/reviews")
    fun createReview(
        @Path("id") poemId: String,
        @Field("text") reviewText: String,
        @Field("rating") reviewRating: Float): Call<NetworkResponse<ReviewResponse>>

    @FormUrlEncoded
    @PUT("reviews/{id}")
    fun editReview(
        @Path("id") reviewId: String,
        @Field("text") reviewText: String,
        @Field("rating") reviewRating: Float): Call<NetworkResponse<ReviewResponse>>

    @DELETE("reviews/{id}")
    fun deleteReview(
        @Path("id") reviewId: String): Call<NetworkResponse<ReviewResponse>>
    //</editor-fold>
}