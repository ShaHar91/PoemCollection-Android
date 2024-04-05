package com.shahar91.poems.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import be.appwise.room.BaseEntity
import com.shahar91.poems.data.local.utils.DBConstants
import java.util.Date

@Entity(tableName = DBConstants.POEM_TABLE_NAME)
data class PoemEntity(
    @PrimaryKey
    @ColumnInfo(name = DBConstants.COLUMN_ID_POEM)
    override val id: String = "",
    val title: String = "",
    val body: String = "",
    val userId: String = "",
    val averageRating: Float = 0f,
    val totalRatingCount: List<Int> = emptyList(),
    val createdAt: Date? = null
) : BaseEntity

data class PoemWithRelations(
    @Embedded val poem: PoemEntity,
    @Relation(parentColumn = "userId", entityColumn = "id")
    val user: UserEntity,
    @Relation(
        parentColumn = DBConstants.COLUMN_ID_POEM,
        entityColumn = DBConstants.COLUMN_ID_CATEGORY,
        associateBy = Junction(PoemCategoryCrossRef::class)
    )
    val categories: List<CategoryEntity>,
    @Relation(parentColumn = DBConstants.COLUMN_ID_POEM, entityColumn = DBConstants.COLUMN_ID_POEM)
    val shortReviewList: List<ReviewEntity>

    //https://medium.com/androiddevelopers/database-relations-with-room-544ab95e4542
)


data class PoemWithUser(
    @Embedded val poem: PoemEntity,
    @Relation(parentColumn = "userId", entityColumn = "id")
    val user: UserEntity
)
