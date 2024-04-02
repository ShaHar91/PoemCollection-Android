package com.shahar91.poems.data.remote.models

import com.shahar91.poems.data.local.entities.UserEntity

data class UserResponse(
    val _id: String = "",
    var email: String = "",
    var username: String = "",
    var pictureUrl: String? = null
) {
    fun getAsEntity() = UserEntity(_id, email, username, pictureUrl)
}