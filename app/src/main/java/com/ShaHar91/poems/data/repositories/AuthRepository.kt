package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import be.appwise.core.extensions.logging.logv
import be.appwise.core.networking.NetworkConstants
import be.appwise.core.networking.Networking
import com.shahar91.poems.networking.ApiCallsManager
import io.reactivex.android.schedulers.AndroidSchedulers

object AuthRepository : BaseRepository() {

    @JvmStatic
    fun loginUser(email: String, password: String, onSuccess: () -> Unit, onError:(Throwable) -> Unit) {
        addCall(ApiCallsManager.loginUser(email, password).observeOn(AndroidSchedulers.mainThread()).subscribe({
            logv(null, "Successfully logged in")

            //Save token
            Networking.saveAccessToken(it.apply { token_type = NetworkConstants.BEARER.trim() })

            UserRepository.getCurrentUser(onSuccess, onError)
        }, {
            onError(it)
        }))
    }
}