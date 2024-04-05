package com.shahar91.poems.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.shahar91.poems.data.local.utils.DBConstants

@Entity(primaryKeys = [DBConstants.COLUMN_ID_POEM, DBConstants.COLUMN_ID_CATEGORY])
data class PoemCategoryCrossRef(
    val poemId: String,
    val categoryId: String
)

// https://stackoverflow.com/a/58424784/2263408
data class CategoryWithPoems(
    @Embedded
    val category: CategoryEntity,
    @Relation(
        parentColumn = "categoryId",
        entity = PoemEntity::class,
        entityColumn = "poemId",
        associateBy = Junction(
            value = PoemCategoryCrossRef::class,
            parentColumn = "categoryId",
            entityColumn = "poemId"),)
    val poems: List<PoemWithUser>
)