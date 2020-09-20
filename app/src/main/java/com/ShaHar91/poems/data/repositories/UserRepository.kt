package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import be.appwise.core.extensions.logging.logd
import com.shahar91.poems.data.dao.UserDao
import com.shahar91.poems.data.models.User
import com.shahar91.poems.networking.ApiCallsManager
import com.shahar91.poems.utils.HawkUtils.hawkCurrentUserId
import io.reactivex.android.schedulers.AndroidSchedulers

object UserRepository : BaseRepository() {
    private val userDao = UserDao(realm)
    
    @JvmStatic
    fun getCurrentUser(onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.getCurrentUser().observeOn(AndroidSchedulers.mainThread()).subscribe({
            logd(null,"refreshUserFlow call okay")

            //Save current user id in Hawk
            val currentUser = userDao.createOrUpdateObjectFromJson(User::class.java, it.data.toString())
            hawkCurrentUserId = currentUser._id ?: ""
        }, {
            onError(it)
        }, {
            onSuccess()
        }))
    }
}