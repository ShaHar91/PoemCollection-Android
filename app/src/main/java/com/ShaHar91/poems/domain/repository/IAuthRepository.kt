package com.shahar91.poems.domain.repository

import be.appwise.networking.model.AccessToken

interface IAuthRepository {
    suspend fun loginUser(email: String, password: String)

    suspend fun registerUser(userName: String, email: String, password: String)

    suspend fun saveAccessTokenAndGetCurrentUser(accessToken: AccessToken)
}