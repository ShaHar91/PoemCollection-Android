package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.data.dao.ReviewDao
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.utils.HawkUtils
import com.shahar91.poems.utils.protectedClient

object ReviewRepository : BaseRepository() {
    private val reviewDao = ReviewDao(realm)

    fun getOwnReviewForPoemLive(poemId: String) = reviewDao.getOwnReviewForPoemLive(poemId, HawkUtils.hawkCurrentUserId)
    fun getOwnReviewForPoemRealm(poemId: String, userId: String) = reviewDao.getOwnReviewForPoemRealm(poemId, userId)

    suspend fun getReviews(poemId: String) {
        doCall(protectedClient().getReviewsByPoemId(poemId)).data?.let {
            reviewDao.saveAll(it, true)
        }
    }

    suspend fun getOwnReviewForPoemCr(poemId: String) {
        doCall(protectedClient().getOwnReviewForPoem(poemId, HawkUtils.hawkCurrentUserId)).let {
            if (it.data != null && it.data?.size()?.equals(0) == false) {
                reviewDao.createOrUpdateAllFromJson(Review::class.java, it.data.toString())
            } else {
                reviewDao.findAndDeleteReviewForPoemByUserId(poemId, HawkUtils.hawkCurrentUserId)
            }
        }
    }

    suspend fun createReviewCr(poemId: String, reviewText: String, reviewRating: Float) {
        doCall(protectedClient().createReview(poemId, reviewText, reviewRating)).data?.let {
            reviewDao.createOrUpdateObjectFromJson(Review::class.java, it.toString())
        }
    }

    suspend fun updateReviewCr(reviewId: String, reviewText: String, reviewRating: Float) {
        doCall(protectedClient().editReview(reviewId, reviewText, reviewRating)).data?.let {
            reviewDao.createOrUpdateObjectFromJson(Review::class.java, it.toString())
        }
    }

    suspend fun deleteReviewCr(reviewId: String) {
        doCall(protectedClient().deleteReview(reviewId)).let {
            if (it.success) {
                reviewDao.findAndDeleteReviewById(reviewId)
            }
        }
    }
}