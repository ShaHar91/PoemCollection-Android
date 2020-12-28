package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import com.shahar91.poems.data.dao.ReviewDao
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.networking.ProtectedRestClient
import com.shahar91.poems.utils.HawkUtils

object ReviewRepository : BaseRepository() {
    private val protectedClient = ProtectedRestClient.getService
    private val reviewDao = ReviewDao(realm)

    @JvmStatic
    fun getReviews(poemId: String, onSuccess: (List<Review>) -> Unit, onError: (Throwable) -> Unit) {
        //        ApiCallsManager.getAllReviewsForPoem(poemId).observeOn(AndroidSchedulers.mainThread()).subscribe({
        //            if (it.data != null) {
        //                reviewDao.createOrUpdateAllFromJson(Review::class.java, it.data!!.toString())
        //            }
        //        }, {
        //            onError(it)
        //        }, {
        //            onSuccess(reviewDao.findAllReviewsForPoem(poemId))
        //        })
    }

    fun getOwnReviewForPoemLive(poemId: String) = reviewDao.getOwnReviewForPoemLive(poemId, HawkUtils.hawkCurrentUserId)
    fun getOwnReviewForPoemRealm(poemId: String, userId: String) = reviewDao.getOwnReviewForPoemRealm(poemId, userId)

    suspend fun getOwnReviewForPoemCr(poemId: String) {
        doCall(protectedClient.getOwnReviewForPoem(poemId, HawkUtils.hawkCurrentUserId)).let {
            if (it.data != null && it.data?.size()?.equals(0) == false) {
                reviewDao.createOrUpdateAllFromJson(Review::class.java, it.data.toString())
            } else {
                reviewDao.findAndDeleteReviewForPoemByUserId(poemId, HawkUtils.hawkCurrentUserId)
            }
        }
    }

    suspend fun updateReviewCr(reviewId: String, reviewText: String, reviewRating: Float) {
        doCall(protectedClient.editReview(reviewId, reviewText, reviewRating)).data?.let {
            reviewDao.createOrUpdateObjectFromJson(Review::class.java, it.toString())
        }
    }

    suspend fun createReviewCr(poemId: String, reviewText: String, reviewRating: Float) {
        doCall(protectedClient.createReview(poemId, reviewText, reviewRating)).data?.let {
            reviewDao.createOrUpdateObjectFromJson(Review::class.java, it.toString())
        }
    }

    suspend fun deleteReviewCr(reviewId: String) {
        doCall(protectedClient.deleteReview(reviewId)).let {
            if (it.success) {
                reviewDao.findAndDeleteReviewById(reviewId)
            }
        }
    }
}