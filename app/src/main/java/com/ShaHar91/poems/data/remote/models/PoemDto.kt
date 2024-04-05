package com.shahar91.poems.data.remote.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class PoemDto(
    @SerializedName("_id", alternate = ["id"])
    var id: String = "",
    var title: String = "",
    var body: String = "",
    var user: UserDto = UserDto(),
    var categories: List<CategoryDto> = emptyList(),
    var averageRating: Float = 0f,
    var totalRatingCount: List<Int> = emptyList(),
    var shortReviewList: List<ReviewDto> = emptyList(),
    var createdAt: Date? = null
)