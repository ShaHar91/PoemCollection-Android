package com.shahar91.poems.ui.home.poemsPerCategoryList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.databinding.ListItemPoemPerCategoryBinding
import be.appwise.core.ui.base.list.BaseAdapter
import be.appwise.core.ui.base.list.BaseViewHolder
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter.PoemsPerCategoryListInteractionListener

class PoemsPerCategoryListAdapter(private val listener: PoemsPerCategoryListInteractionListener) :
    BaseAdapter<Poem, PoemsPerCategoryListInteractionListener, BaseViewHolder<Poem>>() {

    interface PoemsPerCategoryListInteractionListener {
        fun onPoemClicked(poemId: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemsPerCategoryListViewHolder {
        return PoemsPerCategoryListViewHolder(
            ListItemPoemPerCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class PoemsPerCategoryListViewHolder(private val binding: ListItemPoemPerCategoryBinding) :
        BaseViewHolder<Poem>(binding.root) {

        override fun bind(item: Poem) {
            binding.poem = item
            binding.root.setOnClickListener { listener.onPoemClicked(item._id) }
        }
    }
}