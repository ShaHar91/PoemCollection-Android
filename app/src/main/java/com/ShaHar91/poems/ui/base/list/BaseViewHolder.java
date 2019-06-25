package com.shahar91.poems.ui.base.list;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T, L> extends RecyclerView.ViewHolder {
    public Context context;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    public abstract void bind(int position, T item, L listener);
}