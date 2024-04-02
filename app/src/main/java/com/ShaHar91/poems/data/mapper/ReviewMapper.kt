package com.shahar91.poems.data.mapper

import com.shahar91.poems.data.local.entities.ReviewEntity
import com.shahar91.poems.data.local.entities.ReviewWithUser
import com.shahar91.poems.domain.model.Review

fun ReviewEntity.toReview() = Review(id, text, rating, createdAt)
fun ReviewWithUser.toReview() = review.toReview().copy(user = user.toUser())

fun List<ReviewWithUser>.toReviews() = this.map { it.toReview() }