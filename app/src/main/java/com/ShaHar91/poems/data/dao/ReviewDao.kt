package com.shahar91.poems.data.dao

import be.appwise.core.data.base.BaseDao
import be.appwise.core.data.realmLiveData.RealmLiveData
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.data.models.ReviewFields
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.kotlin.where

class ReviewDao(db: Realm) : BaseDao<Review>(db) {
    private fun where(): RealmQuery<Review> {
        return db.where()
    }

    fun findAll(): List<Review> {
        return where().findAll()
    }

    fun findAllReviewsForPoem(poemId: String): List<Review> {
        return where().equalTo(ReviewFields.POEM._ID, poemId).findAll()
    }

    fun findReviewForPoemByUserId(poemId: String, userId: String?): Review? {
        return where().equalTo(ReviewFields.POEM._ID, poemId).and().equalTo(ReviewFields.USER._ID, userId).findFirst()
    }

    fun findAndDeleteReviewById(reviewId: String) {
        where().equalTo(ReviewFields._ID, reviewId).findFirst()?.apply { delete(this) }
    }

    fun findAndDeleteReviewForPoemByUserId(poemId: String, userId: String?) {
        findReviewForPoemByUserId(poemId, userId)?.apply { delete(this) }
    }

    fun getOwnReviewForPoemLive(poemId: String, userId: String?) = RealmLiveData(where().equalTo(ReviewFields.POEM._ID, poemId).and().equalTo(ReviewFields.USER._ID, userId).findFirstAsync())
}