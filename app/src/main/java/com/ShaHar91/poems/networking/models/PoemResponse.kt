package com.shahar91.poems.networking.models

import com.shahar91.poems.data.models.Poem
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
    fun getAsEntity() = Poem(_id, title, body, user._id, averageRating, totalRatingCount, createdAt)

    fun getCategoriesAsEntities() = categories.map { it.getAsEntity() }

    fun getReviewsAsEntities() = shortReviewList.map { it.getAsEntity(_id) }

    fun getUserAsEntity() = user.getAsEntity()
}