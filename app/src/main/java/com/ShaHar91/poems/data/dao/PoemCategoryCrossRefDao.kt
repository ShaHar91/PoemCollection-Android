package com.shahar91.poems.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shahar91.poems.data.models.PoemCategoryCrossRef
import com.shahar91.poems.data.models.CategoryWithPoems

@Dao
interface PoemCategoryCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoemCategoryCrossRef(crossRef: PoemCategoryCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertManyPoemCategoryCrossRef(crossRef: List<PoemCategoryCrossRef>)

    // https://stackoverflow.com/a/58424784/2263408
    @Query("SELECT * FROM category WHERE categoryId = :categoryId")
    fun findAllPoemsByCategoryId(categoryId: String): LiveData<CategoryWithPoems>
}