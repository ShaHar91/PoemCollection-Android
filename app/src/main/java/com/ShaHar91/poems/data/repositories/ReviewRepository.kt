package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import be.appwise.core.extensions.logging.logd
import com.shahar91.poems.data.dao.ReviewDao
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.networking.ApiCallsManager
import com.shahar91.poems.utils.HawkUtils
import io.reactivex.android.schedulers.AndroidSchedulers

object ReviewRepository : BaseRepository() {
    private val reviewDao = ReviewDao(realm)

    @JvmStatic
    fun getReviews(poemId: String, onSuccess: (List<Review>) -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.getAllReviewsForPoem(poemId).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it.data != null) {
                reviewDao.createOrUpdateAllFromJson(Review::class.java, it.data!!.toString())
            }
        }, {
            onError(it)
        }, {
            onSuccess(reviewDao.findAllReviewsForPoem(poemId))
        }))
    }

    @JvmStatic
    fun getOwnReviewForPoem(poemId: String, onSuccess: (Review?) -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.getOwnReviewForPoem(poemId, HawkUtils.hawkCurrentUserId).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it.data != null && it.data?.size()?.equals(0) == false) {
                reviewDao.createOrUpdateAllFromJson(Review::class.java, it.data!!.toString())
            } else {
                reviewDao.findAndDeleteReviewForPoemByUserId(poemId, HawkUtils.hawkCurrentUserId)
            }
        }, {
            onError(it)
        }, {
            onSuccess(reviewDao.findReviewForPoemByUserId(poemId, HawkUtils.hawkCurrentUserId))
        }))
    }

    @JvmStatic
    fun createReview(poemId: String, reviewText: String, reviewRating: Float, onSuccess: (Review?) -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.createReview(poemId, reviewText, reviewRating).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it.data != null) {
                reviewDao.createOrUpdateObjectFromJson(Review::class.java, it.data!!.toString())
            }
        }, {
            onError(it)
        }, {
            onSuccess(reviewDao.findReviewForPoemByUserId(poemId, HawkUtils.hawkCurrentUserId))
        }))
    }

    @JvmStatic
    fun updateReview(poemId: String, reviewId: String, reviewText: String, reviewRating: Float, onSuccess: (Review?) -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.editReview(reviewId, reviewText, reviewRating).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it.data != null) {
                reviewDao.createOrUpdateObjectFromJson(Review::class.java, it.data!!.toString())
            }
        }, {
            onError(it)
        }, {
            onSuccess(reviewDao.findReviewForPoemByUserId(poemId, HawkUtils.hawkCurrentUserId))
        }))
    }

    @JvmStatic
    fun deleteReview(reviewId: String, onSuccess: () -> Unit, onError:(Throwable) -> Unit) {
        addCall(ApiCallsManager.deleteReview(reviewId).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it?.success == true){
                reviewDao.findAndDeleteReviewById(reviewId)
            }
        }, {
            onError(it)
        }, {
            onSuccess()
        }))
    }
}