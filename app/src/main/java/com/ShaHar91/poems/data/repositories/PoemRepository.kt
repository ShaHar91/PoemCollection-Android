package com.shahar91.poems.data.repositories

import androidx.lifecycle.map
import be.appwise.networking.base.BaseRepository
import com.shahar91.poems.data.local.TransactionProvider
import com.shahar91.poems.data.local.dao.CategoryDao
import com.shahar91.poems.data.local.dao.PoemCategoryCrossRefDao
import com.shahar91.poems.data.local.dao.PoemDao
import com.shahar91.poems.data.local.dao.ReviewDao
import com.shahar91.poems.data.local.dao.UserDao
import com.shahar91.poems.data.local.entities.PoemCategoryCrossRef
import com.shahar91.poems.data.mapper.toEntities
import com.shahar91.poems.data.mapper.toEntity
import com.shahar91.poems.data.mapper.toPoem
import com.shahar91.poems.data.mapper.toPoems
import com.shahar91.poems.data.remote.models.PoemDto
import com.shahar91.poems.data.remote.services.PoemService
import com.shahar91.poems.domain.repository.IPoemRepository
import com.shahar91.poems.utils.HawkManager

class PoemRepository(
    private val poemDao: PoemDao,
    private val categoryDao: CategoryDao,
    private val userDao: UserDao,
    private val reviewDao: ReviewDao,
    private val poemCategoryCrossRefDao: PoemCategoryCrossRefDao,
    private val transactionProvider: TransactionProvider,
    private val protectedPoemService: PoemService,
    private val unProtectedPoemService: PoemService
) : BaseRepository, IPoemRepository {

    override fun findAllPoemsForCategoryLive(categoryId: String) = poemCategoryCrossRefDao.findAllPoemsByCategoryId(categoryId).map { it.poems.toPoems() }
    override fun findPoemByIdLive(poemId: String) = poemDao.getPoemByIdLive(poemId).map { it.toPoem() }

    override suspend fun fetchPoemsForCategory(categoryId: String) {
        val poemResponseList = doCall(unProtectedPoemService.fetchPoemsForCategoryId(categoryId)).data ?: return
        // TODO: instead of this forEach, save everything in bulk!!
        poemResponseList.forEach {
            //TODO: delete other poems/reviews/poemCategoryCrossRef that are saved in Room but did not came with the Response
            savePoem(it)
        }
    }

    override suspend fun fetchPoemById(poemId: String) {
        val poemResponse = doCall(unProtectedPoemService.fetchPoemById(poemId, HawkManager.currentUserId)).data ?: return
        savePoem(poemResponse)
    }

    override suspend fun createPoem(poemTitle: String, poemBody: String, categoryList: List<String>) {
        val poemResponse = doCall(protectedPoemService.createPoem(poemTitle, poemBody, categoryList)).data ?: return
        savePoem(poemResponse)
    }

    override suspend fun savePoem(poemResponse: PoemDto) {
        transactionProvider.runAsTransaction {
            poemDao.insert(poemResponse.toEntity())
            userDao.insert(poemResponse.user.toEntity())
            categoryDao.insertMany(poemResponse.categories.toEntities())
            reviewDao.insertMany(poemResponse.shortReviewList.map { it.toEntity(poemResponse.id)})
            userDao.insertMany(poemResponse.shortReviewList.map { it.user.toEntity() })

            val list = poemResponse.categories.map {
                PoemCategoryCrossRef(poemResponse.id, it.id)
            }

            poemCategoryCrossRefDao.insertManyPoemCategoryCrossRef(list)
        }
    }
}