package com.shahar91.poems.data.dao

import be.appwise.core.data.base.BaseDao
import be.appwise.core.data.realmLiveData.RealmLiveData
import be.appwise.core.data.realmLiveData.RealmResultsLiveData
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.models.PoemFields
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.kotlin.where

class PoemDao(db: Realm) : BaseDao<Poem>(db) {
    private fun where(): RealmQuery<Poem> {
        return db.where()
    }

    fun findAllPoemsByCategoryId(categoryId: String):  List<Poem>{
        return where().equalTo(PoemFields.CATEGORIES._ID, categoryId).findAll()
    }

    fun findPoemById(poemId: String): Poem? {
        return where().equalTo(PoemFields._ID, poemId).findFirst()
    }

    fun getPoemsForCategoryLive(categoryId: String) = RealmResultsLiveData(where().equalTo(PoemFields.CATEGORIES._ID, categoryId).findAllAsync())

    fun getPoemByIdLive(poemId: String) = RealmLiveData(where().equalTo(PoemFields._ID, poemId).findFirstAsync())
}