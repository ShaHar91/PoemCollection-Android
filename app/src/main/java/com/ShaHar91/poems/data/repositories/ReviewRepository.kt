package com.shahar91.poems.data.repositories

import androidx.lifecycle.LiveData
import be.appwise.networking.base.BaseRepository
import com.shahar91.poems.data.dao.ReviewDao
import com.shahar91.poems.data.models.ReviewWithUser
import com.shahar91.poems.networking.NewApiManagerService
import com.shahar91.poems.utils.HawkUtils

class ReviewRepository(
    private val reviewDao: ReviewDao,
    private val protectedService: NewApiManagerService
) : BaseRepository {

    fun findOwnReviewForPoemLive(poemId: String) = reviewDao.findOwnReviewForPoemLive(poemId, HawkUtils.hawkCurrentUserId)
    fun findReviewsForPoem(poemId: String) = reviewDao.findAllReviewsForPoem(poemId, HawkUtils.hawkCurrentUserId ?: "")

    suspend fun getOwnReviewForPoem(poemId: String) {
        doCall(protectedService.getOwnReviewForPoem(poemId, HawkUtils.hawkCurrentUserId)).data?.let { reviewResponseList ->
            if (reviewResponseList.isNotEmpty()) {
                reviewDao.insertMany(reviewResponseList.map { it.getAsEntity() })
            } else {
                // In case the user's review has not been returned, we should the delete the one we have in Room
                // maybe the user deleted it on another device, or an admin deleted the review
                reviewDao.findAndDeleteReviewForPoemByUserId(poemId, HawkUtils.hawkCurrentUserId)
            }
        }
    }

    suspend fun createReview(poemId: String, reviewText: String, reviewRating: Float) {
        doCall(protectedService.createReview(poemId, reviewText, reviewRating)).data?.let {
            reviewDao.insert(it.getAsEntity())
        }
    }

    suspend fun updateReview(reviewId: String, reviewText: String, reviewRating: Float) {
        doCall(protectedService.editReview(reviewId, reviewText, reviewRating)).data?.let {
            reviewDao.insert(it.getAsEntity())
        }
    }

    suspend fun deleteReview(reviewId: String) {
        doCall(protectedService.deleteReview(reviewId)).let {
            if (it.success) {
                reviewDao.deleteById(reviewId)
            }
        }
    }
}