package com.shahar91.poems.ui.base.list

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T, L>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var context: Context = itemView.context
    abstract fun bind(position: Int, item: T, listener: L)
}