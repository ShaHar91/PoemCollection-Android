package com.shahar91.poems.data.repositories

import androidx.lifecycle.map
import be.appwise.networking.base.BaseRepository
import com.shahar91.poems.data.local.dao.UserDao
import com.shahar91.poems.data.mapper.toUser
import com.shahar91.poems.data.remote.services.AuthService
import com.shahar91.poems.domain.repository.IUserRepository
import com.shahar91.poems.utils.HawkManager

class UserRepository(
    private val userDao: UserDao,
    private val protectedService: AuthService
) : BaseRepository, IUserRepository {

    override fun findCurrentUser() = userDao.findFirstById(HawkManager.currentUserId ?: "").map { it.toUser() }

    override suspend fun fetchCurrentUser() {
        doCall(protectedService.fetchCurrentUser()).data?.let {
            userDao.insert(it.getAsEntity())
            HawkManager.currentUserId = it._id
        }
    }
}