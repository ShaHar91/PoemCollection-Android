package com.shahar91.poems.data.remote.models

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("_id", alternate = ["id"])
    val id: String = "",
    val name: String = ""
)