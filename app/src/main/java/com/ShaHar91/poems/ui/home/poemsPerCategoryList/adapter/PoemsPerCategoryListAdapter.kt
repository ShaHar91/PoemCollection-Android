package com.shahar91.poems.ui.home.poemsPerCategoryList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.databinding.ListItemPoemPerCategoryBinding
import be.appwise.core.ui.base.list.BaseAdapter
import be.appwise.core.ui.base.list.BaseViewHolder
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter.PoemsPerCategoryListInteractionListener

class PoemsPerCategoryListAdapter(context: Context, listener: PoemsPerCategoryListInteractionListener) :
    BaseAdapter<Poem, PoemsPerCategoryListInteractionListener, BaseViewHolder<Poem, PoemsPerCategoryListInteractionListener>>(
        context, listener) {

    interface PoemsPerCategoryListInteractionListener {
        fun onPoemClicked(poemId: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemsPerCategoryListViewHolder {
        return PoemsPerCategoryListViewHolder(
            ListItemPoemPerCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    class PoemsPerCategoryListViewHolder(private val binding: ListItemPoemPerCategoryBinding) :
        BaseViewHolder<Poem, PoemsPerCategoryListInteractionListener>(binding.root) {

        override fun bind(position: Int, item: Poem, listener: PoemsPerCategoryListInteractionListener) {
            binding.poem = item
            binding.root.setOnClickListener { listener.onPoemClicked(item._id) }
        }
    }
}