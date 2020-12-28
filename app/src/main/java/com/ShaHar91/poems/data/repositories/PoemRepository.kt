package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.data.dao.PoemDao
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.networking.UnProtectedRestClient
import com.shahar91.poems.utils.HawkUtils

object PoemRepository : BaseRepository() {
    private val unprotectedClient = UnProtectedRestClient.getService
    private val poemDao = PoemDao(realm)

    @JvmStatic
    fun getPoems(categoryId: String, onSuccess: (List<Poem>) -> Unit, onError: (Throwable) -> Unit) {
        //        ApiCallsManager.getAllPoems(categoryId)
        //            .observeOn(AndroidSchedulers.mainThread())
        //            .subscribe({
        //                it.data?.let { data ->
        //                    poemDao.createOrUpdateAllFromJson(Poem::class.java, data.toString())
        //                }
        //            }, {
        //                onError(it)
        //            }, {
        //                onSuccess(poemDao.findAllPoemsByCategoryId(categoryId))
        //            })
    }

    @JvmStatic
    fun getPoemById(poemId: String, onSuccess: (Poem?) -> Unit, onError: (Throwable) -> Unit) {
        //        ApiCallsManager.getPoemById(poemId, HawkUtils.hawkCurrentUserId)
        //            .observeOn(AndroidSchedulers.mainThread())
        //            .subscribe({
        //                it.data?.let { data ->
        //                    poemDao.copyOrUpdate(data)
        //                }
        //            }, {
        //                onError(it)
        //            }, {
        //                onSuccess(poemDao.findPoemById(poemId))
        //            })
    }

    @JvmStatic
    fun createPoem(poemTitle: String, poemBody: String, categoryList: List<String>, onSuccess: () -> Unit,
        onError: (Throwable) -> Unit) {
        //        ApiCallsManager.createPoem(poemTitle, poemBody, categoryList)
        //            .observeOn(AndroidSchedulers.mainThread())
        //            .subscribe({
        //                it.data?.let { data ->
        //                    poemDao.copyOrUpdate(data)
        //                }
        //            }, {
        //                onError(it)
        //            }, {
        //                onSuccess()
        //            })
    }

    fun getPoemsForCategoryLive(categoryId: String) = poemDao.getPoemsForCategoryLive(categoryId)

    suspend fun getPoemsForCategoryCr(categoryId: String) {
        doCall(unprotectedClient.getPoemsForCategoryId(categoryId)).data?.let {
            poemDao.createOrUpdateAllFromJson(Poem::class.java, it.toString())
        }
    }

    fun getPoemByIdLive(poemId: String) = poemDao.getPoemByIdLive(poemId)
    fun getPoemByIdRealm(poemId: String) = poemDao.getPoemByIdRealm(poemId)

    suspend fun getPoemByIdCr(poemId: String) {
        doCall(unprotectedClient.getPoemById(poemId, HawkUtils.hawkCurrentUserId)).data?.let {
            poemDao.copyOrUpdate(it)
        }
    }
}