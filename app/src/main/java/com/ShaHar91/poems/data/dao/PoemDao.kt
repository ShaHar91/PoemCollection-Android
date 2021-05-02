package com.shahar91.poems.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import be.appwise.core.data.base.BaseRoomDao
import com.shahar91.poems.data.DBConstants
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.data.models.PoemCategoryCrossRef
import com.shahar91.poems.data.models.PoemWithRelations

@Dao
abstract class PoemDao : BaseRoomDao<Poem>(DBConstants.POEM_TABLE_NAME) {
    override val idColumnInfo = DBConstants.COLUMN_ID_POEM

    fun findAllPoemsByCategoryId(categoryId: String): List<Poem> {
        return emptyList()
        //        return where().equalTo(PoemFields.CATEGORIES._ID, categoryId).findAll()
    }

    fun findPoemById(poemId: String): Poem? {
        return null
        //        return where().equalTo(PoemFields._ID, poemId).findFirst()
    }

    @Query("SELECT * FROM ${DBConstants.POEM_TABLE_NAME}")
    abstract fun getPoemsForCategoryLive(): LiveData<List<Poem>>

    @Query("SELECT * FROM ${DBConstants.POEM_TABLE_NAME} WHERE ${DBConstants.COLUMN_ID_POEM} = :poemId")
    abstract fun getPoemByIdLive(poemId: String): LiveData<Poem>

    @Query("SELECT * FROM ${DBConstants.POEM_TABLE_NAME} WHERE ${DBConstants.COLUMN_ID_POEM} = :poemId")
    abstract suspend fun getPoemByIdRealm(poemId: String): Poem

    @Transaction
    open suspend fun insertPoemWithRelations(poem: Poem, userDao: UserDao, poemCategoryCrossRefDao: PoemCategoryCrossRefDao, reviewDao: ReviewDao) {
        poem.userId = poem.user?.id ?: ""
        poem.user?.let { user ->
            poem.userId = user.id
            userDao.insert(user)
        }

        poem.categories.forEach { category ->
            poemCategoryCrossRefDao.insertPoemCategoryCrossRef(PoemCategoryCrossRef(poem.id, category.id))
        }

        poem.shortReviewList.forEach {
            reviewDao.insert(it.apply { poemId = poem.id })
        }

        insert(poem)
    }

    @Transaction
    open suspend fun insertPoemsWithRelations(poems: List<Poem>, userDao: UserDao, poemCategoryCrossRefDao: PoemCategoryCrossRefDao, reviewDao: ReviewDao) {
        poems.forEach {
            insertPoemWithRelations(it, userDao, poemCategoryCrossRefDao, reviewDao)
        }
    }

    @Transaction
    @Query("SELECT * FROM ${DBConstants.POEM_TABLE_NAME}")
    abstract suspend fun getPoemWithRelations(): List<PoemWithRelations>
}