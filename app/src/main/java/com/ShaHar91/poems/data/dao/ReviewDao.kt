package com.shahar91.poems.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import be.appwise.core.data.base.BaseRoomDao
import com.shahar91.poems.data.DBConstants
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.data.models.ReviewWithUser

@Dao
abstract class ReviewDao : BaseRoomDao<Review>(DBConstants.REVIEW_TABLE_NAME) {

    fun findAll(): List<Review> {
        return emptyList()
        //        return where().findAll()
    }

    fun findAllReviewsForPoem(poemId: String): List<Review> {
        return emptyList()
        //        return where().equalTo(ReviewFields.POEM._ID, poemId).findAll()
    }

    fun findReviewForPoemByUserId(poemId: String, userId: String?): Review? {
        return null
        //        return where().equalTo(ReviewFields.POEM._ID, poemId).and().equalTo(ReviewFields.USER._ID, userId).findFirst()
    }

    @Transaction
    open suspend fun findAndDeleteReviewForPoemByUserId(poemId: String, userId: String?) {
        val reviewWithUser = findOwnReviewForPoem(poemId, userId)
        if (reviewWithUser != null) {
            delete(reviewWithUser.review)
        }
    }

//    fun findAndDeleteReviewForPoemByUserId(poemId: String, userId: String?) {
//        //        findReviewForPoemByUserId(poemId, userId)?.apply { delete(this) }
//    }

    @Query("SELECT * FROM ${DBConstants.REVIEW_TABLE_NAME} WHERE ${DBConstants.COLUMN_ID_POEM} = :poemId AND userId = :userId")
    abstract fun findOwnReviewForPoemLive(poemId: String, userId: String?): LiveData<ReviewWithUser?>

    @Query("SELECT * FROM ${DBConstants.REVIEW_TABLE_NAME} WHERE ${DBConstants.COLUMN_ID_POEM} = :poemId AND userId = :userId")
    abstract suspend fun findOwnReviewForPoem(poemId: String, userId: String?): ReviewWithUser?
}