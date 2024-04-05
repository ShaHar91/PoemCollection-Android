package com.shahar91.poems.domain.repository

import androidx.lifecycle.LiveData
import com.shahar91.poems.domain.model.User

interface IUserRepository {
    fun findCurrentUser(): LiveData<User>

    suspend fun fetchCurrentUser()
}