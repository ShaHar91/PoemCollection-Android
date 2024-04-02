package com.shahar91.poems.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.withTransaction
import com.shahar91.poems.data.local.dao.CategoryDao
import com.shahar91.poems.data.local.dao.PoemCategoryCrossRefDao
import com.shahar91.poems.data.local.dao.PoemDao
import com.shahar91.poems.data.local.dao.ReviewDao
import com.shahar91.poems.data.local.dao.UserDao
import com.shahar91.poems.data.local.entities.CategoryEntity
import com.shahar91.poems.data.local.entities.PoemEntity
import com.shahar91.poems.data.local.entities.PoemCategoryCrossRef
import com.shahar91.poems.data.local.entities.ReviewEntity
import com.shahar91.poems.data.local.entities.UserEntity
import com.shahar91.poems.data.local.utils.Converters

@Database(
    entities = [CategoryEntity::class, UserEntity::class, PoemEntity::class, PoemCategoryCrossRef::class, ReviewEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PoemDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "poemCollection.db"
    }

    abstract fun categoryDao(): CategoryDao
    abstract fun userDao(): UserDao
    abstract fun poemDao(): PoemDao
    abstract fun reviewDao(): ReviewDao
    abstract fun poemCategoryCrossRefDao(): PoemCategoryCrossRefDao
}

class TransactionProvider(
    private val db: PoemDatabase
) {
    suspend fun <R> runAsTransaction(block: suspend () -> R): R {
        return db.withTransaction(block)
    }
}