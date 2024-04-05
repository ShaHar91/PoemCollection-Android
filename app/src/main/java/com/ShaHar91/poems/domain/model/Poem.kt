package com.shahar91.poems.domain.model

import java.util.Date

data class Poem(
    val id: String = "",
    val title: String = "",
    val body: String = "",
    val averageRating: Float = 0f,
    val totalRatingCount: List<Int> = emptyList(),
    val createdAt: Date? = null,
    val user: User = User()
) {
    val fiveStarRating get(): Int = totalRatingCount.getOrElse(4) { 0 }

    val fourStarRating get(): Int = totalRatingCount.getOrElse(3) { 0 }

    val threeStarRating get(): Int = totalRatingCount.getOrElse(2) { 0 }

    val twoStarRating get(): Int = totalRatingCount.getOrElse(1) { 0 }

    val oneStarRating get(): Int = totalRatingCount.getOrElse(0) { 0 }

    val total get(): Int = totalRatingCount.sum()
}