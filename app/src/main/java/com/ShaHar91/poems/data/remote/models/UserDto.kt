package com.shahar91.poems.data.remote.models

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("_id", alternate = ["id"])
    val id: String = "",
    var email: String = "",
    var username: String = "",
    var pictureUrl: String? = null
)