package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.data.dao.UserDao
import com.shahar91.poems.data.models.User
import com.shahar91.poems.utils.HawkUtils
import com.shahar91.poems.utils.protectedClient

object UserRepository : BaseRepository() {
    private val userDao = UserDao(realm)

    suspend fun getCurrentUserCr() {
        doCall(protectedClient().getCurrentUser()).data?.let {
            //Save current user id in Hawk
            val currentUser = userDao.createOrUpdateObjectFromJson(User::class.java, it.toString())
            HawkUtils.hawkCurrentUserId = currentUser._id
        }
    }
}