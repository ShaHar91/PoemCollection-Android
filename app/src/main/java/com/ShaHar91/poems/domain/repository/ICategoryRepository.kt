package com.shahar91.poems.domain.repository

import androidx.lifecycle.LiveData
import com.shahar91.poems.domain.model.Category

interface ICategoryRepository {
    fun findAllLive(): LiveData<List<Category>>

    suspend fun fetchCategories()
}