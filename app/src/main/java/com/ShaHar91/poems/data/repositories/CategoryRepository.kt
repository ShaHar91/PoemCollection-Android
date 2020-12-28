package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.data.dao.CategoryDao
import com.shahar91.poems.networking.UnProtectedRestClient

object CategoryRepository : BaseRepository() {
    private val unprotectedService = UnProtectedRestClient.getService
    private val categoryDao = CategoryDao(realm)

    suspend fun getCategoriesCr() {
        doCall(unprotectedService.getCategories()).data?.let {
            categoryDao.saveAll(it, true)
        }
    }

    fun getAllCategoriesLive() = categoryDao.getAllLive()
}