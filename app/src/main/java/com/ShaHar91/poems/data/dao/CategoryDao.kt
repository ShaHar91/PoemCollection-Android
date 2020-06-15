package com.shahar91.poems.data.dao

import be.appwise.core.data.base.BaseDao
import com.shahar91.poems.data.models.Category
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.kotlin.where

class CategoryDao(db: Realm) : BaseDao<Category>(db) {
    private fun where(): RealmQuery<Category> {
        return db.where()
    }

    fun findAllCategories(): List<Category> {
        return where().findAll()
    }
}