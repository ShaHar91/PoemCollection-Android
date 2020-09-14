package com.shahar91.poems.ui.home.poemsPerCategoryList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shahar91.poems.R
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.ui.base.list.BaseAdapter
import com.shahar91.poems.ui.base.list.BaseViewHolder
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter.PoemsPerCategoryListInteractionListener
import kotlinx.android.synthetic.main.list_item_poem_per_category.view.*

class PoemsPerCategoryListAdapter(context: Context, listener: PoemsPerCategoryListInteractionListener) :
    BaseAdapter<Poem, PoemsPerCategoryListInteractionListener, BaseViewHolder<Poem, PoemsPerCategoryListInteractionListener>>(
        context, listener) {

    interface PoemsPerCategoryListInteractionListener {
        fun onPoemClicked(poemId: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoemsPerCategoryListViewHolder {
        return PoemsPerCategoryListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_poem_per_category, parent, false))
    }

    class PoemsPerCategoryListViewHolder(itemView: View) :
        BaseViewHolder<Poem, PoemsPerCategoryListInteractionListener>(itemView) {

        override fun bind(position: Int, item: Poem, listener: PoemsPerCategoryListInteractionListener) {
            itemView.titleTv.text = item.title
            itemView.writerTv.text = item.user.username
            itemView.setOnClickListener { listener.onPoemClicked(item.id) }
        }
    }
}