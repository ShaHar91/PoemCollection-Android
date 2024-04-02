package com.shahar91.poems.data.mapper

import com.shahar91.poems.data.local.entities.UserEntity
import com.shahar91.poems.data.remote.models.UserDto
import com.shahar91.poems.domain.model.User

fun UserEntity.toUser() = User(id, email, username, pictureUrl)

fun UserDto.toEntity() = UserEntity(id, email, username, pictureUrl)

fun List<UserDto>.toEntities() = this.map { it.toEntity() }