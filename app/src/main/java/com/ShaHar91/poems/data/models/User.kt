package com.shahar91.poems.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
    @PrimaryKey
    var _id: String = "",
    var email: String = "",
    var username: String = "",
    var pictureUrl: String? = null) : RealmObject()