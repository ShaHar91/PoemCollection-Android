package com.shahar91.poems.data.models

import androidx.room.Entity
import com.shahar91.poems.data.DBConstants

@Entity(primaryKeys = [DBConstants.COLUMN_ID_POEM, DBConstants.COLUMN_ID_CATEGORY])
data class PoemCategoryCrossRef(
    val poemId: String,
    val categoryId: String
)



































