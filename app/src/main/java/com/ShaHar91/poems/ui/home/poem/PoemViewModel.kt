package com.shahar91.poems.ui.home.poem

import androidx.lifecycle.LiveData
import be.appwise.core.extensions.viewmodel.singleArgViewModelFactory
import be.appwise.core.util.SingleLiveEvent
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.data.repositories.ReviewRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel
import com.shahar91.poems.utils.HawkUtils.hawkCurrentUserId

class PoemViewModel(private val poemId: String) : PoemBaseViewModel() {
    companion object {
        val FACTORY = singleArgViewModelFactory(::PoemViewModel)
    }

    private val refreshLayout = SingleLiveEvent<Boolean>()
    fun getRefreshLayout(): SingleLiveEvent<Boolean> {
        return refreshLayout
    }

    var poem = PoemRepository.getPoemByIdLive(poemId)

    //    private val _ownReview = MutableLiveData<Boolean>().apply { value = false }
    //    val ownReview get() = _ownReview as LiveData<Boolean>

    var ownReview: LiveData<Review?> = ReviewRepository.getOwnReviewForPoemLive(poemId)

    fun getPoemAndAllDataCr(rating: Float? = null) = launchAndLoad {
        this.delayedRating = rating
        PoemRepository.getPoemByIdCr(poemId)
        if (hawkCurrentUserId != null && hawkCurrentUserId!!.isNotEmpty()) {
            ReviewRepository.getOwnReviewForPoemCr(poemId)
        }
        refreshLayout.value = true
    }

    var delayedRating: Float? = null
        private set

    fun saveOrUpdateReview(reviewId: String?, newReviewText: String, newRating: Float) = launchAndLoad {
        if (reviewId != null) {
            // Update Review
            ReviewRepository.updateReviewCr(reviewId, newReviewText, newRating)
        } else {
            // New Review
            //TODO: create a new Review does not update the layout
            ReviewRepository.createReviewCr(poemId, newReviewText, newRating)
        }
        refreshLayout.value = true
    }


    fun deleteReview(reviewId: String) = launchAndLoad {
        //TODO: deleting a Review does not update the layout
        ReviewRepository.deleteReviewCr(reviewId)
        refreshLayout.value = true
    }

    fun resetRating() {
        delayedRating = null
    }
}