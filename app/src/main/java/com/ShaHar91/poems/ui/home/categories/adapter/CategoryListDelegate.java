package com.shahar91.poems.ui.home.categories.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.shahar91.poems.R;
import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.ui.base.list.BaseInteractionListener;

import java.util.List;

public class CategoryListDelegate extends AdapterDelegate<Category> {
    private LayoutInflater inflater;
    private BaseInteractionListener listener;

    public CategoryListDelegate(Context context, BaseInteractionListener listener) {
        inflater = ((Activity) context).getLayoutInflater();
        this.listener = listener;
    }

    @Override
    protected boolean isForViewType(@NonNull Category item, int position) {
        return item instanceof Category;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new CategoryViewHolder(inflater.inflate(R.layout.list_item_category, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Category item, int position, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {
        CategoryViewHolder holder = (CategoryViewHolder) viewHolder;
        holder.bind(position, item, listener);
    }
}
