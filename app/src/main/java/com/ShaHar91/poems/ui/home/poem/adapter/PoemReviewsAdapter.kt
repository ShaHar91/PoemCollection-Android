package com.shahar91.poems.ui.home.poem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import be.appwise.core.ui.base.list.BaseViewHolder
import com.shahar91.poems.R
import com.shahar91.poems.databinding.ListItemReviewBinding
import com.shahar91.poems.domain.model.Review

class PoemReviewsAdapter : ListAdapter<Review, PoemReviewsAdapter.ReviewViewHolder>(ReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(ListItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ReviewViewHolder(private val binding: ListItemReviewBinding) :
        BaseViewHolder<Review>(binding.root) {
        override fun bind(item: Review) {
            binding.review = item
            itemView.findViewById<ImageView>(R.id.ivReviewMenu).isGone = true
        }
    }
}

class ReviewDiffCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}