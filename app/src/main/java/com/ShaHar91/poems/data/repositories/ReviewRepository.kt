package com.shahar91.poems.data.repositories

import androidx.lifecycle.map
import be.appwise.networking.base.BaseRepository
import com.shahar91.poems.data.local.dao.ReviewDao
import com.shahar91.poems.data.mapper.toEntity
import com.shahar91.poems.data.mapper.toReview
import com.shahar91.poems.data.mapper.toReviews
import com.shahar91.poems.data.remote.services.ReviewService
import com.shahar91.poems.domain.repository.IReviewRepository
import com.shahar91.poems.utils.HawkManager

class ReviewRepository(
    private val reviewDao: ReviewDao,
    private val protectedService: ReviewService
) : BaseRepository, IReviewRepository {

    override fun findOwnReviewForPoemLive(poemId: String) = reviewDao.findOwnReviewForPoemLive(poemId, HawkManager.currentUserId).map { it?.toReview() }
    override fun findReviewsForPoem(poemId: String) = reviewDao.findAllReviewsForPoem(poemId, HawkManager.currentUserId ?: "").map { it.toReviews() }

    override suspend fun fetchOwnReviewForPoem(poemId: String) {
        val reviewResponseList = doCall(protectedService.fetchOwnReviewForPoem(poemId, HawkManager.currentUserId)).data ?: return

        if (reviewResponseList.isNotEmpty()) {
            reviewDao.insertMany(reviewResponseList.map { it.toEntity() })
        } else {
            // In case the user's review has not been returned, we should the delete the one we have in Room
            // maybe the user deleted it on another device, or an admin deleted the review
            reviewDao.findAndDeleteReviewForPoemByUserId(poemId, HawkManager.currentUserId)
        }
    }

    override suspend fun createReview(poemId: String, reviewText: String, reviewRating: Float) {
        val review = doCall(protectedService.createReview(poemId, reviewText, reviewRating)).data ?: return
        reviewDao.insert(review.toEntity(poemId))
    }

    override suspend fun updateReview(reviewId: String, reviewText: String, reviewRating: Float) {
        val review = doCall(protectedService.editReview(reviewId, reviewText, reviewRating)).data ?: return
        reviewDao.insert(review.toEntity())

    }

    override suspend fun deleteReview(reviewId: String) {
        val success = doCall(protectedService.deleteReview(reviewId)).success
        if (success) {
            reviewDao.deleteById(reviewId)
        }
    }
}