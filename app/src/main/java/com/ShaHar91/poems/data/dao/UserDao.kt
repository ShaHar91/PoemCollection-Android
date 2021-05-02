package com.shahar91.poems.data.dao

import androidx.room.Dao
import androidx.room.Query
import be.appwise.core.data.base.BaseRoomDao
import com.shahar91.poems.data.DBConstants
import com.shahar91.poems.data.models.User

@Dao
abstract class UserDao() : BaseRoomDao<User>(DBConstants.USER_TABLE_NAME) {
    @Query("SELECT * FROM ${DBConstants.USER_TABLE_NAME} WHERE id == :userId")
    abstract fun findFirstById(userId: String): User
}