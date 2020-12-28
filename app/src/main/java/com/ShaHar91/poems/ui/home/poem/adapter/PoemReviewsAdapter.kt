package com.shahar91.poems.ui.home.poem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.databinding.ListItemReviewBinding
import be.appwise.core.ui.base.list.BaseAdapter
import be.appwise.core.ui.base.list.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_review.view.*

class PoemReviewsAdapter(listener: ReviewInteractionListener): BaseAdapter<Review, PoemReviewsAdapter.ReviewInteractionListener, BaseViewHolder<Review>>() {
    interface ReviewInteractionListener

    override fun onCreateViewHolder(parent: ViewGroup,
        viewType: Int): BaseViewHolder<Review> {
        return ReviewViewHolder(ListItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    class ReviewViewHolder(private val binding: ListItemReviewBinding) : BaseViewHolder<Review>(binding.root) {
        override fun bind(item: Review) {
            binding.review = item
            itemView.ivReviewMenu.isGone = true
            itemView.rhUserHeader.userName = item.user?.username ?: ""
            itemView.rhUserHeader.rating = item.rating
        }
    }
}