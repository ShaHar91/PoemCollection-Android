package com.shahar91.poems.networking.models

import com.shahar91.poems.data.models.Category

data class CategoryResponse(
    val _id: String = "",
    val name: String = ""
) {
    fun getAsEntity() = Category(_id, name)
}