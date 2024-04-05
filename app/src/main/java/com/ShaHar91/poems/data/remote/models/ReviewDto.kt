package com.shahar91.poems.data.remote.models

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("_id", alternate = ["id"])
    val id: String = "",
    val text: String = "",
    val rating: Float = 0F,
    @SerializedName("created_at")
    val createdAt: String? = null,
    val poem: PoemDto = PoemDto(),
    val user: UserDto = UserDto()
)