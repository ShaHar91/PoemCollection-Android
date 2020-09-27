package com.shahar91.poems.ui.home.poem

import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.data.repositories.PoemRepository
import com.shahar91.poems.data.repositories.ReviewRepository
import com.shahar91.poems.ui.base.PoemBaseViewModel
import com.shahar91.poems.utils.HawkUtils.hawkCurrentUserId

class PoemViewModel : PoemBaseViewModel() {
    private lateinit var poemId: String
    private lateinit var listener: ViewModelCallbacks

    var totalReviews = 0
        private set
    var poem: Poem? = null
        private set(value) {
            field = value
            totalReviews = 0
            value?.totalRatingCount?.forEach {
                totalReviews += it
            }
        }
    var ownReview: Review? = null
        private set
    var delayedRating: Float? = null
        private set

    fun init(poemId: String, listener: ViewModelCallbacks) {
        this.poemId = poemId
        this.listener = listener
    }

    interface ViewModelCallbacks {
        fun refreshLayout()

        fun error(throwable: Throwable)
    }

    fun getPoemAndAllReviews(rating: Float? = null) {
        getPoemFromBackend {
            if (hawkCurrentUserId != null && hawkCurrentUserId!!.isNotEmpty()) {
                ReviewRepository.getOwnReviewForPoem(poemId, {
                    ownReview = it
                    if (rating != null && rating > 0) {
                        delayedRating = rating
                    }
                    listener.refreshLayout()
                }, {
                    listener.error(it)
                })
            } else {
                listener.refreshLayout()
            }
        }
    }

    private fun getPoemFromBackend(onSuccess: () -> Unit) {
        PoemRepository.getPoemById(poemId, {
            this.poem = it
            onSuccess()
        }, {
            listener.error(it)
        })
    }

    fun saveOrUpdateReview(reviewId: String?, newReviewText: String?, newRating: Float) {
        if (reviewId != null) {
            // Update review
            ReviewRepository.updateReview(poemId, reviewId, newReviewText!!, newRating, {
                this.ownReview = it?.let { Review(it._id, it.text, it.rating, it.createdAt, it.poem, it.user) }
                getPoemFromBackend { listener.refreshLayout() }
            }, {
                listener.error(it)
            })
        } else {
            // new review
            ReviewRepository.createReview(poemId, newReviewText!!, newRating, {
                ownReview = it?.let { Review(it._id, it.text, it.rating, it.createdAt, it.poem, it.user) }
                getPoemFromBackend { listener.refreshLayout() }
            }, {
                listener.error(it)
            })
        }
    }

    fun deleteReview(reviewId: String?) {
        ReviewRepository.deleteReview(reviewId!!, {
            ownReview = null
            getPoemFromBackend { listener.refreshLayout() }
        }, {
            listener.error(it)
        })
    }

    fun resetRating() {
        delayedRating = null
    }
}