package com.shahar91.poems.ui.home.poem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Review
import com.shahar91.poems.databinding.ListItemReviewBinding
import com.shahar91.poems.ui.base.list.BaseAdapter
import com.shahar91.poems.ui.base.list.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_review.view.*

class PoemReviewsAdapter(context: Context, listener: ReviewInteractionListener): BaseAdapter<Review, PoemReviewsAdapter.ReviewInteractionListener, BaseViewHolder<Review, PoemReviewsAdapter.ReviewInteractionListener>>(context, listener) {
    interface ReviewInteractionListener

    override fun onCreateViewHolder(parent: ViewGroup,
        viewType: Int): BaseViewHolder<Review, ReviewInteractionListener> {
        return ReviewViewHolder(ListItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    class ReviewViewHolder(private val binding: ListItemReviewBinding) : BaseViewHolder<Review, ReviewInteractionListener>(binding.root) {
        override fun bind(position: Int, item: Review, listener: ReviewInteractionListener) {
            binding.review = item
            itemView.ivReviewMenu.isGone = true
            itemView.rhUserHeader.userName = item.user?.username ?: ""
            itemView.rhUserHeader.rating = item.rating
        }
    }
}