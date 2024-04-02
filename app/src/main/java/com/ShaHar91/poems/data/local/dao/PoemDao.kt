package com.shahar91.poems.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import be.appwise.room.BaseRoomDao
import com.shahar91.poems.data.local.utils.DBConstants
import com.shahar91.poems.data.local.entities.PoemEntity
import com.shahar91.poems.data.local.entities.PoemWithUser

@Dao
abstract class PoemDao : BaseRoomDao<PoemEntity>(DBConstants.POEM_TABLE_NAME) {
    override val idColumnInfo = DBConstants.COLUMN_ID_POEM

    @Query("SELECT * FROM ${DBConstants.POEM_TABLE_NAME}")
    abstract fun getPoemsForCategoryLive(): LiveData<List<PoemEntity>>

    @Query("SELECT * FROM ${DBConstants.POEM_TABLE_NAME} WHERE ${DBConstants.COLUMN_ID_POEM} = :poemId")
    abstract fun getPoemByIdLive(poemId: String): LiveData<PoemWithUser>
}