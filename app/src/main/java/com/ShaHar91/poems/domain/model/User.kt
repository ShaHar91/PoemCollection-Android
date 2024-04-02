package com.shahar91.poems.domain.model

data class User(
    val id: String = "",
    val email: String = "",
    val username: String = "",
    val pictureUrl: String? = null
) {
    fun getPictureSomething(): String {
        return pictureUrl ?: "https://i.pravatar.cc/150?u=$id"
    }
}
