package com.shahar91.poems.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import be.appwise.room.BaseEntity
import com.shahar91.poems.data.local.utils.DBConstants
import java.util.Date

@Entity(tableName = DBConstants.REVIEW_TABLE_NAME)
data class ReviewEntity(
    @PrimaryKey
    @ColumnInfo(name = DBConstants.COLUMN_ID_REVIEW)
    override val id: String = "",
    val text: String = "",
    val rating: Float = 0F,
    val createdAt: Date? = null,
    val poemId: String = "",
    val userId: String = ""
) : BaseEntity

data class ReviewWithUser(
    @Embedded val review: ReviewEntity,
    @Relation(parentColumn = "userId", entityColumn = "id")
    val user: UserEntity
)