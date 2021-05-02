package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.google.gson.Gson
import com.shahar91.poems.data.dao.PoemCategoryCrossRefDao
import com.shahar91.poems.data.dao.PoemDao
import com.shahar91.poems.data.dao.ReviewDao
import com.shahar91.poems.data.dao.UserDao
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.models.PoemCategoryCrossRef
import com.shahar91.poems.networking.NewApiManagerService
import com.shahar91.poems.utils.HawkUtils

class PoemRepository(
    private val poemDao: PoemDao,
    private val userDao: UserDao,
    private val reviewDao: ReviewDao,
    private val poemCategoryCrossRefDao: PoemCategoryCrossRefDao,
    private val protectedService: NewApiManagerService,
    private val unProtectedService: NewApiManagerService
) : BaseRepository() {
    fun getPoemsForCategoryLive(categoryId: String) = poemCategoryCrossRefDao.findAllPoemsByCategoryId(categoryId)
    fun getPoemByIdLive(poemId: String) = poemDao.getPoemByIdLive(poemId)
    suspend fun getPoemByIdRealm(poemId: String) = poemDao.getPoemByIdRealm(poemId)

    suspend fun getPoemWithRelations() = poemDao.getPoemWithRelations()

    suspend fun getPoemsForCategory(categoryId: String) {
        doCall(unProtectedService.getPoemsForCategoryId(categoryId)).data?.let {
            val poems = Gson().fromJson(it, Array<Poem>::class.java).toList()
            poemDao.insertPoemsWithRelations(poems, userDao, poemCategoryCrossRefDao, reviewDao)
        }
    }

    suspend fun getPoemById(poemId: String) {
        doCall(unProtectedService.getPoemById(poemId, HawkUtils.hawkCurrentUserId)).data?.let {
            poemDao.insertPoemWithRelations(it, userDao, poemCategoryCrossRefDao, reviewDao)
        }
    }

    suspend fun createPoem(poemTitle: String, poemBody: String, categoryList: List<String>) {
        doCall(protectedService.createPoem(poemTitle, poemBody, categoryList)).data?.let {
            poemDao.insert(it)
        }
    }
}