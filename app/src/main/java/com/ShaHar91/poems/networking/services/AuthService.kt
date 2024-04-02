package com.shahar91.poems.networking.services

import be.appwise.networking.NetworkConstants
import be.appwise.networking.base.BaseService
import be.appwise.networking.model.AccessToken
import com.shahar91.poems.networking.NetworkResponse
import com.shahar91.poems.networking.models.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService: BaseService {
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
}