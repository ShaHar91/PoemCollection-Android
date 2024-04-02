package com.shahar91.poems.data.repositories

import be.appwise.networking.base.BaseRepository
import com.shahar91.poems.data.local.TransactionProvider
import com.shahar91.poems.data.local.dao.CategoryDao
import com.shahar91.poems.data.local.dao.PoemCategoryCrossRefDao
import com.shahar91.poems.data.local.dao.PoemDao
import com.shahar91.poems.data.local.dao.ReviewDao
import com.shahar91.poems.data.local.dao.UserDao
import com.shahar91.poems.data.local.entities.PoemCategoryCrossRef
import com.shahar91.poems.data.remote.models.PoemResponse
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

    override fun findAllPoemsForCategoryLive(categoryId: String) = poemCategoryCrossRefDao.findAllPoemsByCategoryId(categoryId)
    override fun findPoemByIdLive(poemId: String) = poemDao.getPoemByIdLive(poemId)

    override suspend fun getPoemsForCategory(categoryId: String) {
        doCall(unProtectedPoemService.getPoemsForCategoryId(categoryId)).data?.let { poemResponseList ->
            transactionProvider.runAsTransaction {
                poemResponseList.forEach {
                    //TODO: delete other poems/reviews/poemCategoryCrossRef that are saved in Room but did not came with the Response
                    savePoem(it)
                }
            }
        }
    }

    override suspend fun getPoemById(poemId: String) {
        doCall(unProtectedPoemService.getPoemById(poemId, HawkManager.currentUserId)).data?.let { poemResponse ->
            transactionProvider.runAsTransaction {
                savePoem(poemResponse)
            }
        }
    }

    override suspend fun createPoem(poemTitle: String, poemBody: String, categoryList: List<String>) {
        doCall(protectedPoemService.createPoem(poemTitle, poemBody, categoryList)).data?.let { poemResponse ->
            transactionProvider.runAsTransaction {
                savePoem(poemResponse)
            }
        }
    }

    override suspend fun savePoem(poemResponse: PoemResponse) {
        transactionProvider.runAsTransaction {
            poemDao.insert(poemResponse.getAsEntity())
            userDao.insert(poemResponse.getUserAsEntity())
            categoryDao.insertMany(poemResponse.getCategoriesAsEntities())
            reviewDao.insertMany(poemResponse.getReviewsAsEntities())
            userDao.insertMany(poemResponse.shortReviewList.map { it.getUserAsEntity() })

            val list = poemResponse.categories.map {
                PoemCategoryCrossRef(poemResponse._id, it._id)
            }

            poemCategoryCrossRefDao.insertManyPoemCategoryCrossRef(list)
        }
    }
}