package com.shahar91.poems.ui.home.poem.adapter

import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.ui.base.list.KotlinEpoxyHolder
import kotlinx.android.synthetic.main.list_item_own_review.view.*

@EpoxyModelClass(layout = R.layout.list_item_own_review)
abstract class PoemOwnReviewModel : EpoxyModelWithHolder<KotlinEpoxyHolder>() {
    interface Listener {
        fun onOwnReviewClicked(review: Review)
    }

    @EpoxyAttribute
    lateinit var review: Review

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: KotlinEpoxyHolder) {
        holder.view.apply {
            rhUserHeader.apply {
                rating = 3f
                userName = "ThisIsSomebodyNex"
                setImage(null, "CB", ContextCompat.getColor(context, R.color.colorPrimary))
            }

            setOnClickListener { listener.onOwnReviewClicked(review) }
        }
    }
}