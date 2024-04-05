package com.shahar91.poems.domain.repository

import androidx.lifecycle.LiveData
import com.shahar91.poems.data.remote.models.PoemDto
import com.shahar91.poems.domain.model.Poem

interface IPoemRepository {
    fun findAllPoemsForCategoryLive(categoryId: String): LiveData<List<Poem>>
    fun findPoemByIdLive(poemId: String): LiveData<Poem>

    suspend fun fetchPoemsForCategory(categoryId: String)

    suspend fun fetchPoemById(poemId: String)

    suspend fun createPoem(poemTitle: String, poemBody: String, categoryList: List<String>)

    suspend fun savePoem(poemResponse: PoemDto)
}