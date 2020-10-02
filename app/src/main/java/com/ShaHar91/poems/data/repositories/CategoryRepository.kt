package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.data.dao.CategoryDao
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.networking.ApiCallsManager
import io.reactivex.android.schedulers.AndroidSchedulers

object CategoryRepository : BaseRepository() {
    private val categoryDao = CategoryDao(realm)

    @JvmStatic
    fun getCategories(onSuccess: (List<Category>) -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.getAllCategories().observeOn(AndroidSchedulers.mainThread()).subscribe({
            it.data?.let { data ->
                categoryDao.saveAll(data, true)
            }
        }, {
            // TODO: offline "feature" can be better!!
            //            if (it.cause is ConnectException) {
            //                onSuccess(categoryDao.findAllCategories())
            //            } else {
            onError(it)
            //            }
        }, {
            onSuccess(categoryDao.findAllCategories())
        }))
    }

    suspend fun getCategoriesCr() {
       ApiCallsManager.getAllCategoriesCr()?.data?.let {
            categoryDao.saveAll(it, true)
        }
    }

    fun getAllCategoriesLive() = categoryDao.getAllLive()
}