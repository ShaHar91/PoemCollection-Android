package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.data.dao.CategoryDao
import com.shahar91.poems.utils.unprotectedClient

object CategoryRepository : BaseRepository() {
    private val categoryDao = CategoryDao(realm)

    fun getAllCategoriesLive() = categoryDao.getAllLive()

    suspend fun getCategories() {
        doCall(unprotectedClient().getCategories()).data?.let {
            categoryDao.saveAll(it, true)
        }
    }
}