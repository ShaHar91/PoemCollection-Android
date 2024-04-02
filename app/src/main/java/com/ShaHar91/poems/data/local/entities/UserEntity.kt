package com.shahar91.poems.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.appwise.room.BaseEntity
import com.shahar91.poems.data.local.utils.DBConstants

@Entity(tableName = DBConstants.USER_TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    override var id: String = "",
    var email: String = "",
    var username: String = "",
    var pictureUrl: String? = null
) : BaseEntity