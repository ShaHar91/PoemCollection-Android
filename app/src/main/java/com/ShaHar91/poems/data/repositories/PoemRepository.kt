package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import be.appwise.core.extensions.logging.logd
import com.shahar91.poems.data.dao.PoemDao
import com.shahar91.poems.networking.ApiCallsManager
import io.reactivex.android.schedulers.AndroidSchedulers

object PoemRepository : BaseRepository() {
    private val poemDao = PoemDao(realm)

    @JvmStatic
    fun getPoems(onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.getAllPoems().observeOn(AndroidSchedulers.mainThread()).subscribe({
            logd(anyObject = it)
        }, { onError(it) }, { onSuccess() }))
    }
}