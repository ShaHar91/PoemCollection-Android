package com.shahar91.poems.data.dao

import be.appwise.core.data.base.BaseDao
import com.shahar91.poems.data.models.User
import com.shahar91.poems.data.models.UserFields
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.kotlin.where

class UserDao(db: Realm) : BaseDao<User>(db) {
    private fun where(): RealmQuery<User> {
        return db.where()
    }

    fun findFirstById(userId: String?): User? {
        return where().equalTo(UserFields._ID, userId).findFirst()
    }
}