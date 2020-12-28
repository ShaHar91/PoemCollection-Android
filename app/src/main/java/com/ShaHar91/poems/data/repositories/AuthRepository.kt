package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import be.appwise.core.networking.Networking
import com.shahar91.poems.Constants
import com.shahar91.poems.networking.UnProtectedRestClient

object AuthRepository : BaseRepository() {
    private val unprotectedService = UnProtectedRestClient.getService

    suspend fun loginUserCr(email: String, password: String) {
        doCall(unprotectedService.loginUser(email, password)).let {
            Networking.saveAccessToken(it.apply { token_type = Constants.NETWORK_BEARER.trim() })
        }

        UserRepository.getCurrentUserCr()
    }

    suspend fun registerUserCr(userName: String, email: String, password: String) {
        val accessToken = doCall(unprotectedService.registerUser(userName, email, password))

        Networking.saveAccessToken(accessToken.apply { token_type = Constants.NETWORK_BEARER.trim() })

        UserRepository.getCurrentUserCr()
    }
}