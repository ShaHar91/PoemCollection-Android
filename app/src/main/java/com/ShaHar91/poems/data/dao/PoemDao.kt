package com.shahar91.poems.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import be.appwise.core.data.base.BaseRoomDao
import com.shahar91.poems.data.DBConstants
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.models.PoemWithUser

@Dao
abstract class PoemDao : BaseRoomDao<Poem>(DBConstants.POEM_TABLE_NAME) {
    override val idColumnInfo = DBConstants.COLUMN_ID_POEM

    @Query("SELECT * FROM ${DBConstants.POEM_TABLE_NAME}")
    abstract fun getPoemsForCategoryLive(): LiveData<List<Poem>>

    @Query("SELECT * FROM ${DBConstants.POEM_TABLE_NAME} WHERE ${DBConstants.COLUMN_ID_POEM} = :poemId")
    abstract fun getPoemByIdLive(poemId: String): LiveData<PoemWithUser>
}