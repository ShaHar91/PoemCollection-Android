package com.shahar91.poems.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Review(
    @PrimaryKey
    var _id: String = "",
    var text: String = "",
    var rating: Float = 0F,
    var createdAt: Date? = null,
    var poem: Poem? = null,
    var user: User? = null) : RealmObject() {
    companion object {
        fun createNewReview(review: Review): Review {
            return Review(review._id, review.text, review.rating, review.createdAt, review.poem,
                review.user)
        }
    }
}