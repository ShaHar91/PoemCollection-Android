package com.shahar91.poems.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shahar91.poems.data.DBConstants
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

@Database(entities = [Category::class, User::class, Poem::class, PoemCategoryCrossRef::class, Review::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PoemDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun userDao(): UserDao
    abstract fun poemDao(): PoemDao
    abstract fun reviewDao(): ReviewDao
    abstract fun poemCategoryCrossRefDao(): PoemCategoryCrossRefDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE: PoemDatabase? = null

        fun getDatabase(context: Context): PoemDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, PoemDatabase::class.java, DBConstants.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}