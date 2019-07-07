package com.shahar91.poems.ui.home.poemsPerCategoryList.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import com.shahar91.poems.R;
import com.shahar91.poems.data.models.PoemsPerCategory;
import com.shahar91.poems.ui.base.list.BaseInteractionListener;

import java.util.List;

public class PoemsPerCategoryListDelegate extends AdapterDelegate<PoemsPerCategory> {
    private LayoutInflater inflater;
    private BaseInteractionListener listener;

    public PoemsPerCategoryListDelegate(Context context, BaseInteractionListener listener) {
        inflater = ((Activity) context).getLayoutInflater();
        this.listener = listener;
    }

    @Override
    protected boolean isForViewType(@NonNull PoemsPerCategory item, int position) {
        return item instanceof PoemsPerCategory;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new PoemsPerCategoryListViewHolder(inflater.inflate(R.layout.list_item_poem_per_category, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull PoemsPerCategory item, int position, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {
        PoemsPerCategoryListViewHolder holder = (PoemsPerCategoryListViewHolder) viewHolder;
        holder.bind(position, item, listener);
    }
}
