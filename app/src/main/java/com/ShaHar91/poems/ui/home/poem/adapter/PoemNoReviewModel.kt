package com.shahar91.poems.ui.home.poem.adapter

import android.os.Handler
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.list.KotlinEpoxyHolder
import kotlinx.android.synthetic.main.list_item_no_review.view.*

@EpoxyModelClass(layout = R.layout.list_item_no_review)
abstract class PoemNoReviewModel : EpoxyModelWithHolder<KotlinEpoxyHolder>() {
    interface Listener {
        fun onRatingBarTouched(rating: Float)
    }

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: KotlinEpoxyHolder) {
        holder.view.rbNoReview.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            run {
                if (fromUser) {
                    listener.onRatingBarTouched(rating)
                    // A delay has to be set in order to show the selected amount of the rating bar
                    // Without it the bar gets updated immediately, not showing any amount or progress
                    Handler().postDelayed({
                        ratingBar.rating = 0f
                    }, 500)
                }
            }
        }
    }
}