package com.shahar91.poems.networking.models

import com.shahar91.poems.data.models.User

data class UserResponse(
    val _id: String = "",
    var email: String = "",
    var username: String = "",
    var pictureUrl: String? = null
) {
    fun getAsEntity() = User(_id, email, username, pictureUrl)
}