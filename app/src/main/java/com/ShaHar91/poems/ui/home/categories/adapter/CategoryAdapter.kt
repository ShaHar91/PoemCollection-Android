package com.shahar91.poems.ui.home.categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.databinding.ListItemCategoryBinding
import com.shahar91.poems.ui.base.list.BaseAdapter
import com.shahar91.poems.ui.base.list.BaseViewHolder
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter.CategoryInteractionListener

class CategoryAdapter(context: Context, listener: CategoryInteractionListener) :
    BaseAdapter<Category, CategoryInteractionListener, BaseViewHolder<Category, CategoryInteractionListener>>(
        context, listener) {

    interface CategoryInteractionListener {
        fun onCategoryClicked(category: Category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class CategoryViewHolder(private val binding: ListItemCategoryBinding) :
        BaseViewHolder<Category, CategoryInteractionListener>(binding.root) {

        override fun bind(position: Int, item: Category,
            listener: CategoryInteractionListener) {
            binding.category = item
            binding.root.setOnClickListener { listener.onCategoryClicked(item) }
        }
    }
}