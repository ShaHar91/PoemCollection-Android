package com.shahar91.poems.ui.home.poem.adapter

import android.content.Context
import com.airbnb.epoxy.Typed2EpoxyController
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.models.Review

class PoemDetailAdapterController(private val context: Context,
    private val listener: Listener) :
    Typed2EpoxyController<Poem, Review?>() {
    interface Listener {
        fun onRatingBarTouched(rating: Float)
        fun onEditReviewClicked(review: Review)
        fun onDeleteReviewClicked(reviewId: String)
    }

    private val reviewListener = object : PoemReviewModel.Listener {
        override fun onEditReviewClicked(review: Review) {
            listener.onEditReviewClicked(review)
        }

        override fun onDeleteReviewClicked(reviewId: String) {
            listener.onDeleteReviewClicked(reviewId)
        }
    }

    override fun buildModels(poem: Poem, ownReview: Review?) {
        poemDetailData {
            this.id(poem._id)
            this.poem(poem)
        }

        if (ownReview == null) {
            poemNoReview {
                this.id("OwnReview")
                this.listener(object : PoemNoReviewModel.Listener {
                    override fun onRatingBarTouched(rating: Float) {
                        listener.onRatingBarTouched(rating)
                    }
                })
            }
        } else {
            poemReview {
                this.id(ownReview._id)
                this.review(ownReview)
                this.listener(reviewListener)
            }
        }

        if (poem.shortReviewList.isNotEmpty()) {
            //TODO: issues exist when adding or removing own review not updating the global review layout due to epoxy's hatefull state and immutable shit....
            poemGlobalRating {
                this.id(poem._id)
                this.averageRating(poem.averageRating)
                this.totalRatingCount(poem.totalRatingCount)
            }

            // TODO: only retrieve the 5 top reviews...(DONE) create new screen to see a paged list of all reviews!!! (TODO)
            poem.shortReviewList.forEach {
                poemReview {
                    this.id(it._id)
                    this.review(it)
                    this.listener(reviewListener)
                }
            }
        }
    }
}