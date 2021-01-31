package com.shahar91.poems.data.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Poem(
    @PrimaryKey
    var _id: String = "",
    var title: String = "",
    var body: String = "",
    var user: User? = null,
    var categories: RealmList<Category> = RealmList(),
    var averageRating: Float = 0f,
    var totalRatingCount: RealmList<Int> = RealmList(),
    var shortReviewList: RealmList<Review> = RealmList(),
    var createdAt: Date? = null) : RealmObject() {

    companion object {
        fun createNewPoem(poem: Poem): Poem {
            return Poem(poem._id, poem.title, poem.body, poem.user, poem.categories,
                poem.averageRating, poem.totalRatingCount, poem.shortReviewList, poem.createdAt)
        }
    }

    fun getFiveStarRating(): Int {
        return if (!totalRatingCount.isNullOrEmpty()) {
            totalRatingCount[4] ?: 0
        } else {
            0
        }
    }

    fun getFourStarRating(): Int {
        return if (!totalRatingCount.isNullOrEmpty()) {
            totalRatingCount[3] ?: 0
        } else {
            0
        }
    }

    fun getThreeStarRating(): Int {
        return if (!totalRatingCount.isNullOrEmpty()) {
            totalRatingCount[2] ?: 0
        } else {
            0
        }
    }

    fun getTwoStarRating(): Int {
        return if (!totalRatingCount.isNullOrEmpty()) {
            totalRatingCount[1] ?: 0
        } else {
            0
        }
    }

    fun getOneStarRating(): Int {
        return if (!totalRatingCount.isNullOrEmpty()) {
            totalRatingCount[0] ?: 0
        } else {
            0
        }
    }

    fun getTotal(): Int {
        var total = 0
        totalRatingCount.forEach { ratingCount ->
            total += ratingCount
        }
        return total
    }
}