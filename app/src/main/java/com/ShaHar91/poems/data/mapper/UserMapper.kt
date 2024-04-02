package com.shahar91.poems.data.mapper

import com.shahar91.poems.data.local.entities.UserEntity
import com.shahar91.poems.domain.model.User

fun UserEntity.toUser() = User(id, email, username, pictureUrl)