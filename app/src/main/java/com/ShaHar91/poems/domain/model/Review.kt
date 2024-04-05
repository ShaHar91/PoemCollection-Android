package com.shahar91.poems.domain.model

import android.text.format.DateUtils
import java.util.Date

data class Review(
    val id: String = "",
    val text: String = "",
    val rating: Float = 0F,
    val createdAt: Date? = null,
    val user: User = User()
) {
    fun getRelativeCreatedDate(): String {
        return DateUtils.getRelativeTimeSpanString(createdAt?.time ?: 0L).toString()
    }
}
