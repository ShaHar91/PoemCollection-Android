package com.shahar91.poems.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.appwise.room.BaseEntity
import com.shahar91.poems.data.local.utils.DBConstants

@Entity(tableName = DBConstants.USER_TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    override val id: String = "",
    val email: String = "",
    val username: String = "",
    val pictureUrl: String? = null
) : BaseEntity