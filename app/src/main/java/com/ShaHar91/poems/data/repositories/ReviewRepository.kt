package com.shahar91.poems.data.repositories

import be.appwise.core.data.base.BaseRepository
import be.appwise.core.networking.Networking
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
    fun getOwnReviewForPoem(poemId: String, userId: String?, onSuccess: (Review?) -> Unit, onError: (Throwable) -> Unit) {
        addCall(ApiCallsManager.getOwnReviewForPoem(poemId, userId).observeOn(AndroidSchedulers.mainThread()).subscribe({
            if (it.data != null) {
                reviewDao.createOrUpdateAllFromJson(Review::class.java, it.data!!.toString())
            }
        }, {
            onError(it)
        }, {
            onSuccess(reviewDao.findReviewForPoemByUserId(poemId, userId))
        }))
    }
}