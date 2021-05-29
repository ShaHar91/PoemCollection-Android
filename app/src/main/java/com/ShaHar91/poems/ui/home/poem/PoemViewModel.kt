package com.shahar91.poems.ui.home.poem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import be.appwise.core.extensions.viewmodel.tripleArgsViewModelFactory
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

    var poemWithUser = poemRepository.findPoemByIdLive(poemId)
    var ownReview = Transformations.switchMap(poemWithUser) {
        reviewRepository.findOwnReviewForPoemLive(poemId)
    }

    var shortReviewList = Transformations.switchMap(poemWithUser) {
        reviewRepository.findReviewsForPoem(poemId)
    }

    private val _delayedRating = MutableLiveData<Float?>(null)
    val delayedRating: LiveData<Float?> get() = _delayedRating

    fun getPoemAndAllDataCr(rating: Float? = null) = launchAndLoad {
        poemRepository.getPoemById(poemId)

        if (HawkUtils.hawkCurrentUserId?.isNotEmpty() == true) {
            reviewRepository.getOwnReviewForPoem(poemId)
        }

        _delayedRating.value = rating
    }

    fun saveOrUpdateReview(reviewId: String?, newReviewText: String, newRating: Float) = launchAndLoad {
        if (reviewId != null) {
            // Update Review
            reviewRepository.updateReview(reviewId, newReviewText, newRating)
        } else {
            // New Review
            //TODO: create a new Review does not update the layout
            reviewRepository.createReview(poemId, newReviewText, newRating)
        }

        getPoemAndAllDataCr()
    }

    fun deleteReview(reviewId: String) = launchAndLoad {
        //TODO: deleting a Review does not update the layout
        reviewRepository.deleteReview(reviewId)
    }

    fun resetRating() {
        _delayedRating.value = null
    }
}