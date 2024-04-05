package com.shahar91.poems.data.local.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    @TypeConverter
    fun fromIntegerList(intList: List<Int>): String {
        return Gson().toJson(intList)
    }

    @TypeConverter
    fun toIntegerList(jsonIntList: String): List<Int> {
        return Gson().fromJson(jsonIntList, Array<Int>::class.java).toList()
    }

    @TypeConverter
    fun fromDate(date: Date?): String {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault()).format(date ?: Date())
    }

    @TypeConverter
    fun toDate(millis: String): Date {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault()).parse(millis) ?: Date()
    }
}