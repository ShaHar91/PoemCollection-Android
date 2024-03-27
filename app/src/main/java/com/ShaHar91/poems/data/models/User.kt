package com.shahar91.poems.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.appwise.room.BaseEntity
import com.shahar91.poems.data.DBConstants

@Entity(tableName = DBConstants.USER_TABLE_NAME)
data class User(
    @PrimaryKey
    override var id: String = "",
    var email: String = "",
    var username: String = "",
    var pictureUrl: String? = null
) : BaseEntity {
    fun getPictureSomething(): String {
        return pictureUrl ?: "https://i.pravatar.cc/150?u=$id"
    }
}