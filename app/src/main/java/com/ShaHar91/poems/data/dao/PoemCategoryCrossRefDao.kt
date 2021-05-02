package com.shahar91.poems.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.shahar91.poems.data.models.PoemCategoryCrossRef

@Dao
interface PoemCategoryCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoemCategoryCrossRef(crossRef: PoemCategoryCrossRef)
}