package com.shahar91.poems.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import be.appwise.room.BaseRoomDao
import com.shahar91.poems.data.local.utils.DBConstants
import com.shahar91.poems.data.local.entities.ReviewEntity
import com.shahar91.poems.data.local.entities.ReviewWithUser

@Dao
abstract class ReviewDao : BaseRoomDao<ReviewEntity>(DBConstants.REVIEW_TABLE_NAME) {

    @Query("SELECT * FROM ${DBConstants.REVIEW_TABLE_NAME} WHERE ${DBConstants.COLUMN_ID_POEM} = :poemId AND userId != :userId")
    abstract fun findAllReviewsForPoem(poemId: String, userId: String): LiveData<List<ReviewWithUser>>

    @Transaction
    open suspend fun findAndDeleteReviewForPoemByUserId(poemId: String, userId: String?) {
        val reviewWithUser = findOwnReviewForPoem(poemId, userId) ?: return
        delete(reviewWithUser.review)
    }

    @Query("SELECT * FROM ${DBConstants.REVIEW_TABLE_NAME} WHERE ${DBConstants.COLUMN_ID_POEM} = :poemId AND userId = :userId")
    abstract fun findOwnReviewForPoemLive(poemId: String, userId: String?): LiveData<ReviewWithUser?>

    @Query("SELECT * FROM ${DBConstants.REVIEW_TABLE_NAME} WHERE ${DBConstants.COLUMN_ID_POEM} = :poemId AND userId = :userId")
    abstract suspend fun findOwnReviewForPoem(poemId: String, userId: String?): ReviewWithUser?
}