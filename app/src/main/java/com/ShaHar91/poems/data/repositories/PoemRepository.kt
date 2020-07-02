package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import be.appwise.core.networking.Networking
import com.shahar91.poems.data.dao.PoemDao
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.networking.ApiCallsManager
import io.reactivex.android.schedulers.AndroidSchedulers

object PoemRepository : BaseRepository() {
    private val poemDao = PoemDao(realm)

    @JvmStatic
    fun getPoems(categoryId: String, onSuccess: (List<Poem>) -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.getAllPoems(categoryId).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it.data != null) {
                poemDao.createOrUpdateAllFromJson(Poem::class.java, it.data!!.toString())
            }
        }, {
            onError(it)
        }, {
            onSuccess(poemDao.findAllPoemsByCategoryId(categoryId))
        }))
    }

    @JvmStatic
    fun getPoemById(poemId: String, userId: String?, onSuccess: (Poem?) -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.getPoemById(poemId, userId).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it.data != null) {
                poemDao.copyOrUpdate(it.data!!)
            }
        }, {
            onError(it)
        }, {
            onSuccess(poemDao.findPoemById(poemId))
        }))
    }
}