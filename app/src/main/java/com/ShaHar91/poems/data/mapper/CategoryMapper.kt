package com.shahar91.poems.data.mapper

import com.shahar91.poems.data.local.entities.CategoryEntity
import com.shahar91.poems.data.remote.models.CategoryDto
import com.shahar91.poems.domain.model.Category

fun CategoryEntity.toCategory() = Category(id, name)

fun List<CategoryEntity>.toCategories() = this.map { it.toCategory() }

fun CategoryDto.toEntity() = CategoryEntity(id, name)

fun List<CategoryDto>.toEntities()= this.map { it.toEntity() }