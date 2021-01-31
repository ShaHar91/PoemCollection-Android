package com.shahar91.poems.ui.home.poem

import androidx.databinding.ObservableField
import be.appwise.core.extensions.viewmodel.singleArgViewModelFactory
import be.appwise.core.util.SingleLiveEvent
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.data.repositories.ReviewRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel
import com.shahar91.poems.utils.HawkUtils

class PoemViewModel(private val poemId: String) : PoemBaseViewModel() {
    companion object {
        val FACTORY = singleArgViewModelFactory(::PoemViewModel)
    }

    var poem = PoemRepository.getPoemByIdRealm(poemId)
    var ownReview: ObservableField<Review?> = ObservableField()

    var delayedRating: Float? = null
        private set

    private val refreshLayout = SingleLiveEvent<Boolean>()
    fun getRefreshLayout(): SingleLiveEvent<Boolean> {
        return refreshLayout
    }

    fun getPoemAndAllDataCr(rating: Float? = null) = launchAndLoad {
        this.delayedRating = rating
        PoemRepository.getPoemById(poemId)
        if (HawkUtils.hawkCurrentUserId?.isNotEmpty() == true) {
            ReviewRepository.getOwnReviewForPoemCr(poemId)
            ownReview.set(ReviewRepository.getOwnReviewForPoemRealm(poemId, HawkUtils.hawkCurrentUserId!!))
        }
        refreshLayout.value = true
    }

    fun saveOrUpdateReview(reviewId: String?, newReviewText: String, newRating: Float) = launchAndLoad {
        if (reviewId != null) {
            // Update Review
            ReviewRepository.updateReviewCr(reviewId, newReviewText, newRating)
        } else {
            // New Review
            //TODO: create a new Review does not update the layout
            ReviewRepository.createReviewCr(poemId, newReviewText, newRating)
        }

        getPoemAndAllDataCr()
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