package com.shahar91.poems.data.repositories

import androidx.lifecycle.LiveData
import be.appwise.networking.base.BaseRepository
import com.shahar91.poems.data.dao.CategoryDao
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.networking.services.CategoryService
import com.shahar91.poems.networking.services.ReviewService
class CategoryRepository(
    private val categoryDao: CategoryDao,
    private val categoryService: CategoryService
) : BaseRepository, ICategoryRepository {

    override fun findAllLive() = categoryDao.findAllLive()

    override suspend fun getCategories() {
        doCall(categoryService.getCategories()).data?.let { categoryResponseList ->
            categoryDao.insertManyDeleteOthers(categoryResponseList.map { it.getAsEntity() })
        }
    }
}

interface ICategoryRepository {
    fun findAllLive(): LiveData<List<Category>>

    suspend fun getCategories()
}

