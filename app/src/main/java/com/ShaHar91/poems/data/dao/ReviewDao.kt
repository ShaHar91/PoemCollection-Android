package com.shahar91.poems.data.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import be.appwise.core.data.base.BaseRoomDao
import com.google.gson.JsonArray
import com.shahar91.poems.data.DBConstants
import com.shahar91.poems.data.models.Review

@Dao
abstract class ReviewDao: BaseRoomDao<Review>(DBConstants.REVIEW_TABLE_NAME) {

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

    fun findAndDeleteReviewForPoemByUserId(poemId: String, userId: String?) {
        //        findReviewForPoemByUserId(poemId, userId)?.apply { delete(this) }
    }

    fun getOwnReviewForPoemLive(poemId: String, userId: String?) = MutableLiveData<Review>()
    //        RealmLiveData(where().equalTo(ReviewFields.POEM._ID, poemId).and().equalTo(ReviewFields.USER._ID, userId).findFirstAsync())

    fun getOwnReviewForPoemRealm(poemId: String, userId: String) = null
    //        where().equalTo(ReviewFields.POEM._ID, poemId).and().equalTo(ReviewFields.USER._ID, userId).findFirst()
}