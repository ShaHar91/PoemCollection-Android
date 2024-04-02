package com.shahar91.poems.ui.home.poem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.shahar91.poems.data.repositories.IPoemRepository
import com.shahar91.poems.data.repositories.IReviewRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel
import com.shahar91.poems.utils.HawkManager

class PoemViewModel(
    private val poemId: String,
    private val poemRepository: IPoemRepository,
    private val reviewRepository: IReviewRepository
) : PoemBaseViewModel() {

    var poemWithUser = poemRepository.findPoemByIdLive(poemId)
    var ownReview = poemWithUser.switchMap {
        reviewRepository.findOwnReviewForPoemLive(poemId)
    }

    var shortReviewList = poemWithUser.switchMap {
        reviewRepository.findReviewsForPoem(poemId)
    }

    private val _delayedRating = MutableLiveData<Float?>(null)
    val delayedRating: LiveData<Float?> get() = _delayedRating

    init {
        getPoemAndAllData()
    }

    fun getPoemAndAllData(rating: Float? = null) = launchAndLoad {
        poemRepository.getPoemById(poemId)

        if (HawkManager.currentUserId?.isNotEmpty() == true) {
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