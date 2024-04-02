package com.shahar91.poems.data.repositories

import be.appwise.networking.Networking
import be.appwise.networking.base.BaseRepository
import be.appwise.networking.model.AccessToken
import com.shahar91.poems.Constants
import com.shahar91.poems.networking.services.AuthService

class AuthRepository(
    private val authService: AuthService,
    private val userRepository: IUserRepository
) : BaseRepository, IAuthRepository {
    override suspend fun loginUser(email: String, password: String) {
        saveAccessTokenAndGetCurrentUser(doCall(authService.loginUser(email, password)))
    }

    override suspend fun registerUser(userName: String, email: String, password: String) {
        saveAccessTokenAndGetCurrentUser(doCall(authService.registerUser(userName, email, password)))
    }

    override suspend fun saveAccessTokenAndGetCurrentUser(accessToken: AccessToken) {
        Networking.saveAccessToken(accessToken.apply { token_type = Constants.NETWORK_BEARER.trim() })
        userRepository.getCurrentUser()
    }
}

interface IAuthRepository {
    suspend fun loginUser(email: String, password: String)

    suspend fun registerUser(userName: String, email: String, password: String)

    suspend fun saveAccessTokenAndGetCurrentUser(accessToken: AccessToken)
}
