package com.shahar91.poems.ui.home.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.databinding.ListItemCategoryBinding
import be.appwise.core.ui.base.list.BaseAdapter
import be.appwise.core.ui.base.list.BaseViewHolder
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter.CategoryInteractionListener

class CategoryAdapter(private val listener: CategoryInteractionListener) :
    BaseAdapter<Category, CategoryInteractionListener, BaseViewHolder<Category>>() {

    interface CategoryInteractionListener {
        fun onCategoryClicked(category: Category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class CategoryViewHolder(private val binding: ListItemCategoryBinding) :
        BaseViewHolder<Category>(binding.root) {

        override fun bind(item: Category) {
            binding.category = item
            binding.root.setOnClickListener { listener.onCategoryClicked(item) }
        }
    }
}