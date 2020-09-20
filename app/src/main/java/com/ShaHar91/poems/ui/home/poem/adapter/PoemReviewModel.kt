package com.shahar91.poems.ui.home.poem.adapter

import android.widget.FrameLayout
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import be.appwise.core.extensions.view.hide
import be.appwise.core.extensions.view.show
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.ui.base.list.KotlinEpoxyHolder
import com.shahar91.poems.utils.HawkUtils
import kotlinx.android.synthetic.main.list_item_review.view.*


@EpoxyModelClass(layout = R.layout.list_item_review)
abstract class PoemReviewModel : EpoxyModelWithHolder<KotlinEpoxyHolder>() {
    interface Listener {
        fun onEditReviewClicked(review: Review)

        fun onDeleteReviewClicked(reviewId: String)
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
                userName = review.user?.username ?: ""
                setImage(null, userName[0].toString(), ContextCompat.getColor(context, R.color.colorPrimary))
            }

            tvReviewBody.text = review.text

            if (review.user?._id != HawkUtils.hawkCurrentUserId) {
                val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT)
                lp.setMargins(holder.view.marginLeft, holder.view.marginTop, holder.view.marginRight, 0)
                holder.view.layoutParams = lp
            }

            if (review.user?._id == HawkUtils.hawkCurrentUserId) {
                holder.view.ivReviewMenu.show()
                holder.view.ivReviewMenu.setOnClickListener {
                    PopupMenu(context, it).apply {
                        menuInflater.inflate(R.menu.menu_review, this.menu)
                        setOnMenuItemClickListener { item ->
                            when (item.itemId) {
                                R.id.review_edit ->
                                    listener.onEditReviewClicked(review)
                                R.id.review_delete ->
                                    listener.onDeleteReviewClicked(review._id)
                            }
                            true
                        }
                        show()
                    }
                }
            } else {
                holder.view.ivReviewMenu.hide()
            }
        }
    }
}