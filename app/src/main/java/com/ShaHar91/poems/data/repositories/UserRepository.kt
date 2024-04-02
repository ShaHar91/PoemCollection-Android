package com.shahar91.poems.data.repositories

import androidx.lifecycle.LiveData
import be.appwise.networking.base.BaseRepository
import com.shahar91.poems.data.dao.UserDao
import com.shahar91.poems.data.models.User
import com.shahar91.poems.networking.services.AuthService
import com.shahar91.poems.networking.services.ReviewService
import com.shahar91.poems.utils.HawkManager

interface IUserRepository {
    fun findCurrentUser(): LiveData<User>

    suspend fun getCurrentUser()
}

class UserRepository(
    private val userDao: UserDao,
    private val protectedService: AuthService
) : BaseRepository, IUserRepository {
    
    override fun findCurrentUser() = userDao.findFirstById(HawkManager.currentUserId ?: "")

    override suspend fun getCurrentUser() {
        doCall(protectedService.getCurrentUser()).data?.let {
            userDao.insert(it.getAsEntity())
            HawkManager.currentUserId = it._id
        }
    }
}