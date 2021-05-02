package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.google.gson.Gson
import com.shahar91.poems.data.dao.ReviewDao
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.networking.NewApiManagerService
import com.shahar91.poems.networking.ProtectedRestClient
import com.shahar91.poems.utils.HawkUtils

class ReviewRepository(
    private val reviewDao: ReviewDao,
    private val protectedService: NewApiManagerService
) : BaseRepository() {

    fun getOwnReviewForPoemLive(poemId: String) = reviewDao.getOwnReviewForPoemLive(poemId, HawkUtils.hawkCurrentUserId)
    fun getOwnReviewForPoemRealm(poemId: String, userId: String) = reviewDao.getOwnReviewForPoemRealm(poemId, userId)

    suspend fun getReviews(poemId: String) {
        doCall(protectedService.getReviewsByPoemId(poemId)).data?.let {
            val reviews = Gson().fromJson(it, Array<Review>::class.java).toList()
            reviewDao.insertManyDeleteOthers(reviews)
        }
    }

    suspend fun getOwnReviewForPoemCr(poemId: String) {
        doCall(protectedService.getOwnReviewForPoem(poemId, HawkUtils.hawkCurrentUserId)).let {
            if (it.data != null && it.data?.size()?.equals(0) == false) {
                val reviews = Gson().fromJson(it.data, Array<Review>::class.java).toList()
                reviewDao.insertMany(reviews)
            } else {
                reviewDao.findAndDeleteReviewForPoemByUserId(poemId, HawkUtils.hawkCurrentUserId)
            }
        }
    }

    suspend fun createReviewCr(poemId: String, reviewText: String, reviewRating: Float) {
        doCall(protectedService.createReview(poemId, reviewText, reviewRating)).data?.let {
            val review = Gson().fromJson(it, Review::class.java)
            reviewDao.insert(review)
        }
    }

    suspend fun updateReviewCr(reviewId: String, reviewText: String, reviewRating: Float) {
        doCall(protectedService.editReview(reviewId, reviewText, reviewRating)).data?.let {
            val review = Gson().fromJson(it, Review::class.java)
            reviewDao.insert(review)
        }
    }

    suspend fun deleteReviewCr(reviewId: String) {
        doCall(protectedService.deleteReview(reviewId)).let {
            if (it.success) {
                reviewDao.deleteById(reviewId)
            }
        }
    }
}