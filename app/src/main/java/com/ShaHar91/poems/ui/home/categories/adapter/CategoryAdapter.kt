package com.shahar91.poems.ui.home.categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Category
import com.shahar91.poems.ui.base.list.BaseAdapter
import com.shahar91.poems.ui.base.list.BaseViewHolder
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter.CategoryInteractionListener
import kotlinx.android.synthetic.main.list_item_category.view.*

class CategoryAdapter(context: Context, listener: CategoryInteractionListener) :
    BaseAdapter<Category, CategoryInteractionListener, BaseViewHolder<Category, CategoryInteractionListener>>(
        context, listener) {

    interface CategoryInteractionListener {
        fun onCategoryClicked(category: Category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_category, parent, false))
    }

    class CategoryViewHolder(itemView: View) :
        BaseViewHolder<Category, CategoryInteractionListener>(itemView) {

        override fun bind(position: Int, item: Category,
            listener: CategoryInteractionListener) {
            itemView.categoryTv.text = item.name
            itemView.setOnClickListener { listener.onCategoryClicked(item) }
        }
    }

}