package com.shahar91.poems.ui.home.poem

import androidx.databinding.ObservableField
import be.appwise.core.extensions.viewmodel.singleArgViewModelFactory
import be.appwise.core.util.SingleLiveEvent
import com.shahar91.poems.MyApp
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.ui.base.PoemBaseViewModel
import com.shahar91.poems.utils.HawkUtils

class PoemViewModel(private val poemId: String) : PoemBaseViewModel() {
    companion object {
        val FACTORY = singleArgViewModelFactory(::PoemViewModel)
    }

    var poem = MyApp.poemRepository.getPoemByIdLive(poemId)
    var ownReview: ObservableField<Review?> = ObservableField()

    var delayedRating: Float? = null
        private set

    private val refreshLayout = SingleLiveEvent<Boolean>()
    fun getRefreshLayout(): SingleLiveEvent<Boolean> {
        return refreshLayout
    }

    fun getPoemAndAllDataCr(rating: Float? = null) = launchAndLoad {
        this.delayedRating = rating
        MyApp.poemRepository.getPoemById(poemId)
        if (HawkUtils.hawkCurrentUserId?.isNotEmpty() == true) {
            MyApp.reviewRepository.getOwnReviewForPoemCr(poemId)
            ownReview.set(MyApp.reviewRepository.getOwnReviewForPoemRealm(poemId, HawkUtils.hawkCurrentUserId!!))
        }
        refreshLayout.value = true
    }

    fun saveOrUpdateReview(reviewId: String?, newReviewText: String, newRating: Float) = launchAndLoad {
        if (reviewId != null) {
            // Update Review
            MyApp.reviewRepository.updateReviewCr(reviewId, newReviewText, newRating)
        } else {
            // New Review
            //TODO: create a new Review does not update the layout
            MyApp.reviewRepository.createReviewCr(poemId, newReviewText, newRating)
        }

        getPoemAndAllDataCr()
    }


    fun deleteReview(reviewId: String) = launchAndLoad {
        //TODO: deleting a Review does not update the layout
        MyApp.reviewRepository.deleteReviewCr(reviewId)
        refreshLayout.value = true
    }

    fun resetRating() {
        delayedRating = null
    }
}