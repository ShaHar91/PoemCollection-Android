package com.shahar91.poems.data.remote.models

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("_id", alternate = ["id"])
    val id: String = "",
    val email: String = "",
    val username: String = "",
    val pictureUrl: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null
)