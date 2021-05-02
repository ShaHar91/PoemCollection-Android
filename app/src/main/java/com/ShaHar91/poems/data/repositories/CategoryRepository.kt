package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.google.gson.Gson
import com.shahar91.poems.data.dao.CategoryDao
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.networking.NewApiManagerService


class CategoryRepository(
    private val categoryDao: CategoryDao,
    private val unprotectedService: NewApiManagerService
) : BaseRepository() {

    fun getAllCategoriesLive() = categoryDao.getAllLive()

    suspend fun getCategories() {
        doCall(unprotectedService.getCategories()).data?.let {
            val categoryList = Gson().fromJson(it, Array<Category>::class.java).toList()
            categoryDao.insertManyDeleteOthers(categoryList)
        }
    }
}