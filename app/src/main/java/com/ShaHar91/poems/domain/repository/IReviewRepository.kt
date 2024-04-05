package com.shahar91.poems.domain.repository

import androidx.lifecycle.LiveData
import com.shahar91.poems.domain.model.Review

interface IReviewRepository {
    fun findOwnReviewForPoemLive(poemId: String): LiveData<Review?>
    fun findReviewsForPoem(poemId: String): LiveData<List<Review>>

    suspend fun fetchOwnReviewForPoem(poemId: String)

    suspend fun createReview(poemId: String, reviewText: String, reviewRating: Float)

    suspend fun updateReview(reviewId: String, reviewText: String, reviewRating: Float)

    suspend fun deleteReview(reviewId: String)
}