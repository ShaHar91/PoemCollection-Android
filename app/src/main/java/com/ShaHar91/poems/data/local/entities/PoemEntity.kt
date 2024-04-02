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
    override var id: String = "",
    var title: String = "",
    var body: String = "",
    var userId: String = "",
    var averageRating: Float = 0f,
    var totalRatingCount: List<Int> = emptyList(),
    var createdAt: Date? = null
) : BaseEntity

data class PoemWithRelations(
    @Embedded var poem: PoemEntity,
    @Relation(parentColumn = "userId", entityColumn = "id")
    var user: UserEntity,
    @Relation(
        parentColumn = DBConstants.COLUMN_ID_POEM,
        entityColumn = DBConstants.COLUMN_ID_CATEGORY,
        associateBy = Junction(PoemCategoryCrossRef::class)
    )
    var categories: List<CategoryEntity>,
    @Relation(parentColumn = DBConstants.COLUMN_ID_POEM, entityColumn = DBConstants.COLUMN_ID_POEM)
    var shortReviewList: List<ReviewEntity>

    //https://medium.com/androiddevelopers/database-relations-with-room-544ab95e4542
)


data class PoemWithUser(
    @Embedded var poem: PoemEntity,
    @Relation(parentColumn = "userId", entityColumn = "id")
    var user: UserEntity
)
