package com.shahar91.poems.ui.home.poem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.shahar91.poems.databinding.ListItemReviewBinding
import be.appwise.core.ui.base.list.BaseViewHolder
import com.shahar91.poems.R
import com.shahar91.poems.data.models.ReviewWithUser

class PoemReviewsAdapter : ListAdapter<ReviewWithUser, PoemReviewsAdapter.ReviewViewHolder>(ReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(ListItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ReviewViewHolder(private val binding: ListItemReviewBinding) :
        BaseViewHolder<ReviewWithUser>(binding.root) {
        override fun bind(item: ReviewWithUser) {
            binding.reviewWithUser = item
            itemView.findViewById<ImageView>(R.id.ivReviewMenu).isGone = true
        }
    }
}

class ReviewDiffCallback : DiffUtil.ItemCallback<ReviewWithUser>() {
    override fun areItemsTheSame(oldItem: ReviewWithUser, newItem: ReviewWithUser): Boolean {
        return oldItem.review.id == newItem.review.id
    }

    override fun areContentsTheSame(oldItem: ReviewWithUser, newItem: ReviewWithUser): Boolean {
        return oldItem == newItem
    }
}