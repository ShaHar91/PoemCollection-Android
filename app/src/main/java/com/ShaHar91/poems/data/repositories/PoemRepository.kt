package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.data.dao.PoemDao
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.utils.HawkUtils
import com.shahar91.poems.utils.protectedClient
import com.shahar91.poems.utils.unprotectedClient

object PoemRepository : BaseRepository() {
    private val poemDao = PoemDao(realm)

    fun getPoemsForCategoryLive(categoryId: String) = poemDao.getPoemsForCategoryLive(categoryId)
    fun getPoemByIdLive(poemId: String) = poemDao.getPoemByIdLive(poemId)
    fun getPoemByIdRealm(poemId: String) = poemDao.getPoemByIdRealm(poemId)

    suspend fun getPoemsForCategory(categoryId: String) {
        doCall(unprotectedClient().getPoemsForCategoryId(categoryId)).data?.let {
            poemDao.createOrUpdateAllFromJson(Poem::class.java, it.toString())
        }
    }

    suspend fun getPoemById(poemId: String) {
        doCall(unprotectedClient().getPoemById(poemId, HawkUtils.hawkCurrentUserId)).data?.let {
            poemDao.copyOrUpdate(it)
        }
    }

    suspend fun createPoem(poemTitle: String, poemBody: String, categoryList: List<String>) {
        doCall(protectedClient().createPoem(poemTitle, poemBody, categoryList)).data?.let {
            poemDao.copyOrUpdate(it)
        }
    }
}