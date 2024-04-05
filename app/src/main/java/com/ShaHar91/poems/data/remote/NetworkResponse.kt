package com.shahar91.poems.data.remote

import com.google.gson.annotations.Expose

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