package com.shahar91.poems.data.mapper

import com.shahar91.poems.data.local.entities.PoemEntity
import com.shahar91.poems.data.local.entities.PoemWithUser
import com.shahar91.poems.data.remote.models.PoemDto
import com.shahar91.poems.domain.model.Poem

fun PoemEntity.toPoem() = Poem(id, title, body,averageRating, totalRatingCount, createdAt)
fun PoemWithUser.toPoem() = poem.toPoem().copy(user = user.toUser())

//fun List<PoemEntity>.toPoems() = this.map { it.toPoem() }
fun List<PoemWithUser>.toPoems() = this.map { it.toPoem() }

//fun List<CategoryWithPoems>.toPoems() = this.map { it.poems.toPoems() }


fun PoemDto.toEntity() = PoemEntity(id, title, body, user.id, averageRating, totalRatingCount, createdAt)

fun List<PoemDto>.toEntities() = this.map { it.toEntity() }
