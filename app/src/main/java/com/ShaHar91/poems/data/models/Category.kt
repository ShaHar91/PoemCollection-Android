package com.shahar91.poems.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import be.appwise.room.BaseEntity
import com.shahar91.poems.data.DBConstants

@Entity(tableName = DBConstants.CATEGORY_TABLE_NAME)
data class Category(
    @PrimaryKey
    @ColumnInfo(name = DBConstants.COLUMN_ID_CATEGORY)
    override var id: String = "",
    var name: String = ""
) : BaseEntity