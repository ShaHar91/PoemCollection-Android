package com.shahar91.poems.data.remote.models

import com.shahar91.poems.data.local.entities.PoemEntity
import java.util.*

data class PoemResponse(
    var _id: String = "",
    var title: String = "",
    var body: String = "",
    var user: UserResponse = UserResponse(),
    var categories: List<CategoryResponse> = emptyList(),
    var averageRating: Float = 0f,
    var totalRatingCount: List<Int> = emptyList(),
    var shortReviewList: List<ReviewResponse> = emptyList(),
    var createdAt: Date? = null
) {
    fun getAsEntity() = PoemEntity(_id, title, body, user._id, averageRating, totalRatingCount, createdAt)

    fun getCategoriesAsEntities() = categories.map { it.getAsEntity() }

    fun getReviewsAsEntities() = shortReviewList.map { it.getAsEntity(_id) }

    fun getUserAsEntity() = user.getAsEntity()
}