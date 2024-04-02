package com.shahar91.poems.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import be.appwise.room.BaseEntity
import com.shahar91.poems.data.local.utils.DBConstants

@Entity(tableName = DBConstants.CATEGORY_TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = DBConstants.COLUMN_ID_CATEGORY)
    override var id: String = "",
    var name: String = ""
) : BaseEntity