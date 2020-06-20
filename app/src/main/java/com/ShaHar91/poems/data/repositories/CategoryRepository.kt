package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import be.appwise.core.networking.Networking
import com.shahar91.poems.data.dao.CategoryDao
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.networking.ApiCallsManager
import io.reactivex.android.schedulers.AndroidSchedulers

object CategoryRepository : BaseRepository() {
    private val categoryDao = CategoryDao(realm)

    @JvmStatic
    fun getCategories(onSuccess: (List<Category>) -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.getAllCategories().observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it.data != null) {
                categoryDao.createOrUpdateAllFromJson(Category::class.java, it.data!!.toString())
            }
        }, {
            onError(it)
        }, {
            onSuccess(categoryDao.findAllCategories())
        }))
    }
}