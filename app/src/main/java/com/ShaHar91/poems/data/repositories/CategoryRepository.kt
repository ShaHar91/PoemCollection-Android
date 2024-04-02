package com.shahar91.poems.data.repositories

import androidx.lifecycle.map
import be.appwise.networking.base.BaseRepository
import com.shahar91.poems.data.local.dao.CategoryDao
import com.shahar91.poems.data.mapper.toCategories
import com.shahar91.poems.data.remote.services.CategoryService
import com.shahar91.poems.domain.repository.ICategoryRepository

class CategoryRepository(
    private val categoryDao: CategoryDao,
    private val categoryService: CategoryService
) : BaseRepository, ICategoryRepository {

    override fun findAllLive() = categoryDao.findAllLive().map { it.toCategories() }

    override suspend fun fetchCategories() {
        doCall(categoryService.fetchCategories()).data?.let { categoryResponseList ->
            categoryDao.insertManyDeleteOthers(categoryResponseList.map { it.getAsEntity() })
        }
    }
}

