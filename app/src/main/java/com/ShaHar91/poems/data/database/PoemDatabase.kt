package com.shahar91.poems.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.withTransaction
import com.shahar91.poems.data.dao.CategoryDao
import com.shahar91.poems.data.dao.PoemCategoryCrossRefDao
import com.shahar91.poems.data.dao.PoemDao
import com.shahar91.poems.data.dao.ReviewDao
import com.shahar91.poems.data.dao.UserDao
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.models.PoemCategoryCrossRef
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.data.models.User

@Database(
    entities = [Category::class, User::class, Poem::class, PoemCategoryCrossRef::class, Review::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PoemDatabase : RoomDatabase() {
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