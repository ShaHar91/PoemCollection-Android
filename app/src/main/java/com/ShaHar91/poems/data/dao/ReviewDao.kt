package com.shahar91.poems.data.dao

import be.appwise.core.data.base.BaseDao
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.models.PoemFields
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
}