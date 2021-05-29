package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.google.gson.Gson
import com.shahar91.poems.data.dao.UserDao
import com.shahar91.poems.data.models.User
import com.shahar91.poems.networking.NewApiManagerService
import com.shahar91.poems.utils.HawkUtils

class UserRepository(
    private val userDao: UserDao,
    private val protectedService: NewApiManagerService
) : BaseRepository() {
    
    fun findCurrentUser() = userDao.findFirstById(HawkUtils.hawkCurrentUserId ?: "")

    suspend fun getCurrentUser() {
        doCall(protectedService.getCurrentUser()).data?.let {
            userDao.insert(it.getAsEntity())
            HawkUtils.hawkCurrentUserId = it._id
        }
    }
}