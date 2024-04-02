package com.shahar91.poems.ui.home.poemsPerCategoryList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import be.appwise.core.ui.base.list.BaseViewHolder
import com.shahar91.poems.data.local.entities.PoemWithUser
import com.shahar91.poems.databinding.ListItemPoemPerCategoryBinding

class PoemsPerCategoryListAdapter(
    private val onPoemClicked: (poemId: String) -> Unit
) : ListAdapter<PoemWithUser, PoemsPerCategoryListAdapter.PoemsPerCategoryListViewHolder>(PoemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemsPerCategoryListViewHolder {
        return PoemsPerCategoryListViewHolder(
            ListItemPoemPerCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PoemsPerCategoryListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PoemsPerCategoryListViewHolder(private val binding: ListItemPoemPerCategoryBinding) :
        BaseViewHolder<PoemWithUser>(binding.root) {

        override fun bind(item: PoemWithUser) {
            binding.poemWithUser = item
            binding.root.setOnClickListener { onPoemClicked(item.poem.id) }
        }
    }
}

class PoemDiffCallback : DiffUtil.ItemCallback<PoemWithUser>() {
    override fun areItemsTheSame(oldItem: PoemWithUser, newItem: PoemWithUser): Boolean {
        return oldItem.poem.id == newItem.poem.id
    }

    override fun areContentsTheSame(oldItem: PoemWithUser, newItem: PoemWithUser): Boolean {
        return oldItem == newItem
    }
}