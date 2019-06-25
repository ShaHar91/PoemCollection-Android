package com.shahar91.poems.ui.base.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, L, VH extends BaseViewHolder<T, L>> extends RecyclerView.Adapter<VH> {
    private List<T> items;
    private L listener;
    private LayoutInflater layoutInflater;

    public BaseAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public abstract VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        T item = items.get(position);
        holder.bind(position, item, listener);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return items;
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public void addItem(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void addItems(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this.items.addAll(items);
        notifyItemRangeInserted(this.items.size() - items.size(), items.size());
    }

    public void addItems(List<T> items, int positionStart) {
        if (items == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        this.items.addAll(positionStart, items);
        notifyItemRangeInserted(positionStart, items.size());
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void remove(T item) {
        int position = items.indexOf(item);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateItem(T itemOld, T itemNew) {
        if (itemOld == null || itemNew == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        int position = items.indexOf(itemOld);
        items.set(position, itemNew);
        notifyItemChanged(position, itemNew);
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @NonNull
    protected View inflate(@LayoutRes final int layout, @Nullable final ViewGroup parent, final boolean attachToRoot) {
        return layoutInflater.inflate(layout, parent, attachToRoot);
    }

    @NonNull
    protected View inflate(@LayoutRes final int layout, @Nullable final ViewGroup parent) {
        return inflate(layout, parent, false);
    }
}
