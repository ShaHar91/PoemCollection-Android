package com.shahar91.poems.ui.home.poemsPerCategoryList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import be.appwise.core.ui.base.list.BaseViewHolder
import com.shahar91.poems.databinding.ListItemPoemPerCategoryBinding
import com.shahar91.poems.domain.model.Poem

class PoemsPerCategoryListAdapter(
    private val onPoemClicked: (poemId: String) -> Unit
) : ListAdapter<Poem, PoemsPerCategoryListAdapter.PoemsPerCategoryListViewHolder>(PoemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemsPerCategoryListViewHolder {
        return PoemsPerCategoryListViewHolder(
            ListItemPoemPerCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PoemsPerCategoryListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PoemsPerCategoryListViewHolder(private val binding: ListItemPoemPerCategoryBinding) :
        BaseViewHolder<Poem>(binding.root) {

        override fun bind(item: Poem) {
            binding.poem = item
            binding.root.setOnClickListener { onPoemClicked(item.id) }
        }
    }
}

class PoemDiffCallback : DiffUtil.ItemCallback<Poem>() {
    override fun areItemsTheSame(oldItem: Poem, newItem: Poem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Poem, newItem: Poem): Boolean {
        return oldItem == newItem
    }
}