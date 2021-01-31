package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import be.appwise.core.networking.Networking
import be.appwise.core.networking.model.AccessToken
import com.shahar91.poems.Constants
import com.shahar91.poems.utils.unprotectedClient

object AuthRepository : BaseRepository() {
    suspend fun loginUser(email: String, password: String) {
        saveAccessTokenAndGetCurrentUser(doCall(unprotectedClient().loginUser(email, password)))
    }

    suspend fun registerUser(userName: String, email: String, password: String) {
        saveAccessTokenAndGetCurrentUser(doCall(unprotectedClient().registerUser(userName, email, password)))
    }

    suspend fun saveAccessTokenAndGetCurrentUser(accessToken: AccessToken) {
        Networking.saveAccessToken(accessToken.apply { token_type = Constants.NETWORK_BEARER.trim() })
        UserRepository.getCurrentUserCr()
    }
}