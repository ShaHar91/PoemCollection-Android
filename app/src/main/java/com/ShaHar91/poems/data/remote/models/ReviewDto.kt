package com.shahar91.poems.data.remote.models

import com.google.gson.annotations.SerializedName
import com.shahar91.poems.data.local.entities.ReviewEntity
import com.shahar91.poems.data.mapper.toEntity
import java.util.*

data class ReviewDto(
    @SerializedName("_id", alternate = ["id"])
    var id: String = "",
    var text: String = "",
    var rating: Float = 0F,
    var createdAt: Date? = null,
    var poem: PoemDto = PoemDto(),
    var user: UserDto = UserDto()
)