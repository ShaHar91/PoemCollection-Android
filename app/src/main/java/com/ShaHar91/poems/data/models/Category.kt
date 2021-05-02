package com.shahar91.poems.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import be.appwise.core.data.base.BaseEntity
import com.google.gson.annotations.SerializedName
import com.shahar91.poems.data.DBConstants

@Entity(tableName = DBConstants.CATEGORY_TABLE_NAME)
data class Category(
    @PrimaryKey
    @SerializedName("_id")
    @ColumnInfo(name = DBConstants.COLUMN_ID_CATEGORY)
    override val id: String = "",
    var name: String = ""
) : BaseEntity()