package com.shahar91.poems.data.repositories

import be.appwise.networking.base.BaseRepository
import com.shahar91.poems.data.dao.CategoryDao
import com.shahar91.poems.networking.NewApiManagerService

class CategoryRepository(
    private val categoryDao: CategoryDao,
    private val unprotectedService: NewApiManagerService
) : BaseRepository {

    fun findAllLive() = categoryDao.findAllLive()

    suspend fun getCategories() {
        doCall(unprotectedService.getCategories()).data?.let { categoryResponseList ->
            categoryDao.insertManyDeleteOthers(categoryResponseList.map { it.getAsEntity() })
        }
    }
}