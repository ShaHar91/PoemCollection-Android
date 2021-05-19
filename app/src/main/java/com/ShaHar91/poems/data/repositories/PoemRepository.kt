package com.shahar91.poems.data.repositories

import androidx.room.withTransaction
import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.data.database.PoemDatabase
import com.shahar91.poems.data.models.PoemCategoryCrossRef
import com.shahar91.poems.networking.NewApiManagerService
import com.shahar91.poems.networking.models.PoemResponse
import com.shahar91.poems.utils.HawkUtils

class PoemRepository(
    private val poemDatabase: PoemDatabase,
//    private val userRepository: UserRepository,
//    private val categoryRepository: CategoryRepository,
//    private val reviewRepository: ReviewRepository,
    private val protectedService: NewApiManagerService,
    private val unProtectedService: NewApiManagerService
) : BaseRepository() {
    private val poemDao = poemDatabase.poemDao()
    private val categoryDao = poemDatabase.categoryDao()
    private val userDao = poemDatabase.userDao()
    private val reviewDao = poemDatabase.reviewDao()
    private val poemCategoryCrossRefDao = poemDatabase.poemCategoryCrossRefDao()

    fun getPoemsForCategoryLive(categoryId: String) = poemCategoryCrossRefDao.findAllPoemsByCategoryId(categoryId)
    fun getPoemByIdLive(poemId: String) = poemDao.getPoemByIdLive(poemId)

    suspend fun getPoemsForCategory(categoryId: String) {
        doCall(unProtectedService.getPoemsForCategoryId(categoryId)).data?.let { poemResponseList ->
            poemDatabase.withTransaction {
                poemResponseList.forEach {
                    //TODO: delete other poems/reviews/poemCategoryCrossRef that are saved in Room but did not came with the Response
                    savePoem(it)
                }
            }
        }
    }

    suspend fun getPoemById(poemId: String) {
        doCall(unProtectedService.getPoemById(poemId, HawkUtils.hawkCurrentUserId)).data?.let { poemResponse ->
            poemDatabase.withTransaction {
                savePoem(poemResponse)
            }
        }
    }

    suspend fun createPoem(poemTitle: String, poemBody: String, categoryList: List<String>) {
        doCall(protectedService.createPoem(poemTitle, poemBody, categoryList)).data?.let { poemResponse ->
            poemDatabase.withTransaction {
                savePoem(poemResponse)
            }
        }
    }

    suspend fun savePoem(poemResponse: PoemResponse) {
        poemDatabase.withTransaction {
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