package com.shahar91.poems.ui.home.poem.adapter

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.list.KotlinEpoxyHolder
import me.zhanghai.android.materialratingbar.MaterialRatingBar

@EpoxyModelClass(layout = R.layout.list_item_no_review)
abstract class PoemNoReviewModel : EpoxyModelWithHolder<PoemNoReviewModel.Holder>() {
    public interface Listener {
        fun onRatingBarTouched(rating: Float)
    }

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: Holder) {
        holder.rbNoReview.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            run {
                if (fromUser) {
                    listener.onRatingBarTouched(rating)
                    ratingBar.rating = 0f
                }
            }
        }
    }

    class Holder() : KotlinEpoxyHolder() {
        val rbNoReview by bind<MaterialRatingBar>(R.id.rbNoReview)
    }
}