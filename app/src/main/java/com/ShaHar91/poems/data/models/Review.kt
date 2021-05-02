package com.shahar91.poems.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import be.appwise.core.data.base.BaseEntity
import com.google.gson.annotations.SerializedName
import com.shahar91.poems.data.DBConstants
import java.util.*

@Entity(tableName = DBConstants.REVIEW_TABLE_NAME)
data class Review(
    @PrimaryKey
    @SerializedName("_id")
    override var id: String = "",
    var text: String = "",
    var rating: Float = 0F,
    var createdAt: Date? = null,
    @Ignore var poem: Poem? = null,
    var poemId: String = "",
    var user: User? = null) : BaseEntity()