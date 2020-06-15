package com.shahar91.poems.data.dao

import be.appwise.core.data.base.BaseDao
import com.shahar91.poems.data.models.Poem
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.kotlin.where

class PoemDao(db: Realm) : BaseDao<Poem>(db) {
    private fun where(): RealmQuery<Poem> {
        return db.where()
    }
}