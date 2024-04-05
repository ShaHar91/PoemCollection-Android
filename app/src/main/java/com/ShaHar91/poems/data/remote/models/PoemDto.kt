package com.shahar91.poems.data.remote.models

import com.google.gson.annotations.SerializedName

data class PoemDto(
    @SerializedName("_id", alternate = ["id"])
    val id: String = "",
    val title: String = "",
    val body: String = "",
    @SerializedName("user", alternate = ["writer"])
    val user: UserDto = UserDto(),
    val categories: List<CategoryDto> = emptyList(),
    val averageRating: Float = 0f,
    val totalRatingCount: List<Int> = emptyList(),
    val shortReviewList: List<ReviewDto> = emptyList(),
    @SerializedName("created_at")
    val createdAt: String? = null
)