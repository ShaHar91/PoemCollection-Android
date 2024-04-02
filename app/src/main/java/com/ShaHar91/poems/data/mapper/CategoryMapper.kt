package com.shahar91.poems.data.mapper

import com.shahar91.poems.data.local.entities.CategoryEntity
import com.shahar91.poems.domain.model.Category

fun CategoryEntity.toCategory() = Category(id, name)

fun List<CategoryEntity>.toCategories() = this.map { it.toCategory() }