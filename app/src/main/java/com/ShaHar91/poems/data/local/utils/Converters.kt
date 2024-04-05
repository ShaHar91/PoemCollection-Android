package com.shahar91.poems.data.local.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
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
    fun fromDate(date: Date): Long {
        return date.time / 1000L
    }

    @TypeConverter
    fun toDate(millis: Long): Date {
        return Date(millis * 1000L)
    }
}