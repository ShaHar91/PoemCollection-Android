package com.shahar91.poems.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import be.appwise.core.data.base.BaseEntity
import com.google.gson.annotations.SerializedName
import com.shahar91.poems.data.DBConstants
import java.util.*

@Entity(tableName = DBConstants.POEM_TABLE_NAME)
data class Poem(
    @PrimaryKey
    @SerializedName("_id")
    @ColumnInfo(name = DBConstants.COLUMN_ID_POEM)
    override var id: String = "",
    var title: String = "",
    var body: String = "",
    var userId: String = "",
    @Ignore var user: User? = null,
    @Ignore var categories: List<Category> = emptyList(),
    var averageRating: Float = 0f,
    var totalRatingCount: List<Int> = emptyList(),
    @Ignore var shortReviewList: List<Review> = emptyList(),
    var createdAt: Date? = null) : BaseEntity() {

    fun getFiveStarRating(): Int {
        return if (!totalRatingCount.isNullOrEmpty()) {
            totalRatingCount[4] ?: 0
        } else {
            0
        }
    }

    fun getFourStarRating(): Int {
        return if (!totalRatingCount.isNullOrEmpty()) {
            totalRatingCount[3] ?: 0
        } else {
            0
        }
    }

    fun getThreeStarRating(): Int {
        return if (!totalRatingCount.isNullOrEmpty()) {
            totalRatingCount[2] ?: 0
        } else {
            0
        }
    }

    fun getTwoStarRating(): Int {
        return if (!totalRatingCount.isNullOrEmpty()) {
            totalRatingCount[1] ?: 0
        } else {
            0
        }
    }

    fun getOneStarRating(): Int {
        return if (!totalRatingCount.isNullOrEmpty()) {
            totalRatingCount[0] ?: 0
        } else {
            0
        }
    }

    fun getTotal(): Int {
        var total = 0
        totalRatingCount.forEach { ratingCount ->
            total += ratingCount
        }
        return total
    }
}

data class PoemWithRelations(
    @Embedded var poem: Poem,
    @Relation(parentColumn = "userId", entityColumn = "id")
    var user: User,
    @Relation(parentColumn = DBConstants.COLUMN_ID_POEM, entityColumn = DBConstants.COLUMN_ID_CATEGORY, associateBy = Junction(PoemCategoryCrossRef::class))
    var categories: List<Category>,
    @Relation(parentColumn = DBConstants.COLUMN_ID_POEM, entityColumn = DBConstants.COLUMN_ID_POEM)
    var shortReviewList: List<Review>

    //https://medium.com/androiddevelopers/database-relations-with-room-544ab95e4542
)