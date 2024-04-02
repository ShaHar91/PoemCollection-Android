package com.shahar91.poems.data.remote.models

import com.shahar91.poems.data.local.entities.ReviewEntity
import java.util.*

data class ReviewResponse(
    var _id: String = "",
    var text: String = "",
    var rating: Float = 0F,
    var createdAt: Date? = null,
    var poem: PoemResponse = PoemResponse(),
    var user: UserResponse = UserResponse()
) {
    fun getAsEntity(poemId: String? = null) = ReviewEntity(_id, text, rating, createdAt, poemId ?: poem._id, user._id)

    fun getUserAsEntity() = user.getAsEntity()
}