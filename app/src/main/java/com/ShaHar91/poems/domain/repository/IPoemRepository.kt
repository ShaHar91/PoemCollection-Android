package com.shahar91.poems.domain.repository

import androidx.lifecycle.LiveData
import com.shahar91.poems.data.local.entities.CategoryWithPoems
import com.shahar91.poems.data.local.entities.PoemWithUser
import com.shahar91.poems.data.remote.models.PoemResponse

interface IPoemRepository {
    fun findAllPoemsForCategoryLive(categoryId: String): LiveData<CategoryWithPoems>
    fun findPoemByIdLive(poemId: String): LiveData<PoemWithUser>

    suspend fun getPoemsForCategory(categoryId: String)

    suspend fun getPoemById(poemId: String)

    suspend fun createPoem(poemTitle: String, poemBody: String, categoryList: List<String>)

    suspend fun savePoem(poemResponse: PoemResponse)
}