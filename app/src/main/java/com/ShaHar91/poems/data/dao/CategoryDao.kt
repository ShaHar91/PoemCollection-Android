package com.shahar91.poems.data.dao

import be.appwise.core.data.base.BaseDao
import be.appwise.core.data.realmLiveData.RealmResultsLiveData
import com.google.gson.JsonArray
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.models.CategoryFields
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

    fun saveAll(jsonArray: JsonArray, deleteOthers: Boolean = false) {
        db.executeTransaction {
            if (deleteOthers) {
                val ids =
                    jsonArray.map { it.asJsonObject.get(CategoryFields._ID).asString }.toTypedArray()
                where().not().`in`(CategoryFields._ID, ids).findAll().deleteAllFromRealm()
            }
            createOrUpdateAllFromJson(Category::class.java, jsonArray.toString())
        }
    }

    fun getAllLive() = RealmResultsLiveData(where().findAllAsync())
}