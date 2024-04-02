package com.shahar91.poems.data.remote.models

import com.shahar91.poems.data.local.entities.CategoryEntity

data class CategoryResponse(
    val _id: String = "",
    val name: String = ""
) {
    fun getAsEntity() = CategoryEntity(_id, name)
}