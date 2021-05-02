package com.shahar91.poems.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import be.appwise.core.data.base.BaseEntity
import com.google.gson.annotations.SerializedName
import com.shahar91.poems.data.DBConstants

@Entity(tableName = DBConstants.USER_TABLE_NAME)
data class User(
    @PrimaryKey
    @SerializedName("_id")
    override val id: String = "",
    var email: String = "",
    var username: String = "",
    var pictureUrl: String? = null) : BaseEntity()