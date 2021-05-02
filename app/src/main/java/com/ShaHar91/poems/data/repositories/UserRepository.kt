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

    suspend fun getCurrentUser() {
        doCall(protectedService.getCurrentUser()).data?.let {
            val user = Gson().fromJson(it, User::class.java)
            userDao.insert(user)
            HawkUtils.hawkCurrentUserId = user.id
        }
    }
}