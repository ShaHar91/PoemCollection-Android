package com.shahar91.poems.networking

import com.google.gson.JsonArray
import com.google.gson.annotations.Expose
import io.realm.RealmList

open class  NetworkResponse <N> {
    @Expose
    var success: Boolean = false
    @Expose
    var count: Int? = 0
    @Expose
    var pagination: Any? = null
    @Expose
    var data: N? = null
}