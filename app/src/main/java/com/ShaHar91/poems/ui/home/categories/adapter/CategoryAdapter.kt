package com.shahar91.poems.ui.home.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import be.appwise.core.ui.base.list.BaseViewHolder
import com.shahar91.poems.databinding.ListItemCategoryBinding
import com.shahar91.poems.domain.model.Category

class CategoryAdapter(
    private val onCategoryClicked: (category: Category) -> Unit
) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class CategoryViewHolder(private val binding: ListItemCategoryBinding) :
        BaseViewHolder<Category>(binding.root) {

        override fun bind(item: Category) {
            binding.category = item
            binding.root.setOnClickListener { onCategoryClicked(item) }
        }
    }
}

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}