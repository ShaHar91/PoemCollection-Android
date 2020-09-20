package com.shahar91.poems.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category(@PrimaryKey var _id: String = "", var name: String = "") : RealmObject()