package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.data.dao.UserDao
import com.shahar91.poems.data.models.User
import com.shahar91.poems.networking.ProtectedRestClient
import com.shahar91.poems.utils.HawkUtils

object UserRepository : BaseRepository() {
    private val protectedService = ProtectedRestClient.getService
    private val userDao = UserDao(realm)

    suspend fun getCurrentUserCr() {
        doCall(protectedService.getCurrentUser()).data?.let {
            //Save current user id in Hawk
            val currentUser = userDao.createOrUpdateObjectFromJson(User::class.java, it.toString())
            HawkUtils.hawkCurrentUserId = currentUser._id
        }
    }
}