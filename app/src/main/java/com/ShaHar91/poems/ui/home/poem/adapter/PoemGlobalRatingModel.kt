package com.shahar91.poems.ui.home.poem.adapter

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.list.KotlinEpoxyHolder
import kotlinx.android.synthetic.main.list_item_global_rating.view.*
import java.text.NumberFormat

@EpoxyModelClass(layout = R.layout.list_item_global_rating)
abstract class PoemGlobalRatingModel : EpoxyModelWithHolder<KotlinEpoxyHolder>() {
    @EpoxyAttribute
    lateinit var totalRatingCount: List<Int>

    @EpoxyAttribute
    var averageRating : Float? = 0.0f

    override fun bind(holder: KotlinEpoxyHolder) {
        var totalReviews = 0
        totalRatingCount.forEach {
            totalReviews += it
        }
        holder.view.apply {
            tvAverageRating.text = String.format("%.1f", averageRating)
            rbTotalRating.rating = averageRating ?: 0.0f
            tvTotalReviews.text = totalReviews.toString()

            pbOneStarRating.apply {
                max = totalReviews
                progress = totalRatingCount[0]
            }

            pbTwoStarRating.apply {
                max = totalReviews
                progress = totalRatingCount[1]
            }

            pbThreeStarRating.apply {
                max = totalReviews
                progress = totalRatingCount[2]
            }

            pbFourStarRating.apply {
                max = totalReviews
                progress = totalRatingCount[3]
            }

            pbFiveStarRating.apply {
                max = totalReviews
                progress = totalRatingCount[4]
            }
        }
    }
}