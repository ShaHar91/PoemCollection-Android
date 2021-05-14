package com.shahar91.poems.ui.home.poem

import androidx.lifecycle.LiveData
import be.appwise.core.extensions.viewmodel.tripleArgsViewModelFactory
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.data.repositories.ReviewRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel
import com.shahar91.poems.utils.HawkUtils

class PoemViewModel(
    private val poemId: String,
    private val poemRepository: PoemRepository,
    private val reviewRepository: ReviewRepository
) : PoemBaseViewModel() {
    companion object {
        val FACTORY = tripleArgsViewModelFactory(::PoemViewModel)
    }

    var poemWithUser = poemRepository.getPoemByIdLive(poemId)
    var ownReview = reviewRepository.findOwnReviewForPoemLive(poemId)

    var delayedRating: Float? = null
        private set

    fun getPoemAndAllDataCr(rating: Float? = null) = launchAndLoad {
        this.delayedRating = rating

        poemRepository.getPoemById(poemId)

        if (HawkUtils.hawkCurrentUserId?.isNotEmpty() == true) {
            reviewRepository.getOwnReviewForPoemCr(poemId)
        }
    }

    fun saveOrUpdateReview(reviewId: String?, newReviewText: String, newRating: Float) = launchAndLoad {
        if (reviewId != null) {
            // Update Review
            reviewRepository.updateReviewCr(reviewId, newReviewText, newRating)
        } else {
            // New Review
            //TODO: create a new Review does not update the layout
            reviewRepository.createReviewCr(poemId, newReviewText, newRating)
        }

        getPoemAndAllDataCr()
    }

    fun deleteReview(reviewId: String) = launchAndLoad {
        //TODO: deleting a Review does not update the layout
        reviewRepository.deleteReviewCr(reviewId)
    }

    fun resetRating() {
        delayedRating = null
    }
}