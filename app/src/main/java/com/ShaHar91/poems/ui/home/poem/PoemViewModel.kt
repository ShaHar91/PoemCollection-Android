package com.shahar91.poems.ui.home.poem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahar91.poems.domain.repository.IPoemRepository
import com.shahar91.poems.domain.repository.IReviewRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel
import com.shahar91.poems.utils.HawkManager

class PoemViewModel(
    private val poemId: String,
    private val poemRepository: IPoemRepository,
    private val reviewRepository: IReviewRepository
) : PoemBaseViewModel() {

    var poemWithUser = poemRepository.findPoemByIdLive(poemId)
    var ownReview = reviewRepository.findOwnReviewForPoemLive(poemId)
    var shortReviewList = reviewRepository.findReviewsForPoem(poemId)

    private val _delayedRating = MutableLiveData<Float?>(null)
    val delayedRating: LiveData<Float?> get() = _delayedRating

    init {
        getPoemAndAllData()
    }

    fun getPoemAndAllData(rating: Float? = null) = launchAndLoad {
        poemRepository.fetchPoemById(poemId)

        if (HawkManager.currentUserId?.isNotEmpty() == true) {
            reviewRepository.fetchOwnReviewForPoem(poemId)
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

        getPoemAndAllData()
    }

    fun deleteReview(reviewId: String) = launchAndLoad {
        //TODO: deleting a Review does not update the layout
        reviewRepository.deleteReview(reviewId)
    }

    fun resetRating() {
        _delayedRating.value = null
    }
}