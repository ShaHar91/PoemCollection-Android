package com.shahar91.poems.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import be.appwise.room.BaseRoomDao
import com.shahar91.poems.data.local.utils.DBConstants
import com.shahar91.poems.data.local.entities.UserEntity

@Dao
abstract class UserDao : BaseRoomDao<UserEntity>(DBConstants.USER_TABLE_NAME) {

    @Query("SELECT * FROM ${DBConstants.USER_TABLE_NAME} WHERE id == :userId")
    abstract fun findFirstById(userId: String): LiveData<UserEntity>
}