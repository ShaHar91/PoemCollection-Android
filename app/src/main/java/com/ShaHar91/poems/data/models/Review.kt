package com.shahar91.poems.data.models

import android.text.format.DateUtils
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import be.appwise.room.BaseEntity
import com.shahar91.poems.data.DBConstants
import java.util.Date

@Entity(tableName = DBConstants.REVIEW_TABLE_NAME)
data class Review(
    @PrimaryKey
    @ColumnInfo(name = DBConstants.COLUMN_ID_REVIEW)
    override var id: String = "",
    var text: String = "",
    var rating: Float = 0F,
    var createdAt: Date? = null,
    var poemId: String = "",
    var userId: String = ""
) : BaseEntity {

    fun getRelativeCreatedDate(): String {
        return DateUtils.getRelativeTimeSpanString(createdAt?.time ?: 0L).toString()
    }
}

data class ReviewWithUser(
    @Embedded var review: Review,
    @Relation(parentColumn = "userId", entityColumn = "id")
    var user: User
)