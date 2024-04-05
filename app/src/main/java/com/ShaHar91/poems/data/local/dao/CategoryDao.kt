package com.shahar91.poems.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import be.appwise.room.BaseRoomDao
import com.shahar91.poems.data.local.utils.DBConstants
import com.shahar91.poems.data.local.entities.CategoryEntity

@Dao
abstract class CategoryDao : BaseRoomDao<CategoryEntity>(DBConstants.CATEGORY_TABLE_NAME) {
    override val idColumnInfo = DBConstants.COLUMN_ID_CATEGORY

    @Query("SELECT * FROM ${DBConstants.CATEGORY_TABLE_NAME} ORDER BY UPPER(name)")
    abstract fun findAllLive(): LiveData<List<CategoryEntity>>
}