package com.shahar91.poems.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import be.appwise.core.data.base.BaseRoomDao
import com.shahar91.poems.data.DBConstants
import com.shahar91.poems.data.models.Category

@Dao
abstract class CategoryDao : BaseRoomDao<Category>(DBConstants.CATEGORY_TABLE_NAME) {
    override val idColumnInfo = DBConstants.COLUMN_ID_CATEGORY

    @Query("SELECT * FROM ${DBConstants.CATEGORY_TABLE_NAME} ORDER BY UPPER(name)")
    abstract fun getAllLive(): LiveData<List<Category>>
}