package com.shahar91.poems.ui.home.poem.adapter

import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.ui.base.list.KotlinEpoxyHolder
import kotlinx.android.synthetic.main.list_item_review.view.*

@EpoxyModelClass(layout = R.layout.list_item_review)
abstract class PoemReviewModel : EpoxyModelWithHolder<KotlinEpoxyHolder>() {
    interface Listener {
        fun onEditReviewClicked(review: Review)
    }

    @EpoxyAttribute
    lateinit var review: Review

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var listener: Listener

    override fun bind(holder: KotlinEpoxyHolder) {
        //TODO: use PopupMenu for the "report buttons"!!!
        // https://medium.com/keepsafe-engineering/building-a-custom-overflow-menu-aaa09b0b9054
        holder.view.apply {
            rhUserHeader.apply {
                rating = review.rating
                userName = review.user.username
                setImage(null, userName[0].toString(), ContextCompat.getColor(context, R.color.colorPrimary))
            }

            tvReviewTitle.text = review.title
            tvReviewBody.text = review.text

//            setOnClickListener { listener.onEditReviewClicked(review) }
        }
    }
}