package com.shahar91.poems.ui.home.categories.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.shahar91.poems.R;
import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.ui.base.list.BaseAdapter;
import com.shahar91.poems.ui.base.list.BaseInteractionListener;
import com.shahar91.poems.ui.base.list.BaseViewHolder;

import butterknife.BindView;


public class CategoryAdapter extends BaseAdapter<Category, BaseInteractionListener, BaseViewHolder<Category, BaseInteractionListener>> {
    private final CategoryInteractionListener listener;

    public CategoryAdapter(Context context, CategoryInteractionListener listener) {
        super(context);

        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<Category, BaseInteractionListener> holder, int position) {
        holder.bind(position, getItem(position), listener);
    }

    static class CategoryViewHolder extends BaseViewHolder<Category, BaseInteractionListener> {
        @BindView(R.id.categoryTv)
        TextView categoryTv;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bind(int position, Category item, BaseInteractionListener baseInteractionListener) {
            CategoryInteractionListener listener = (CategoryInteractionListener) baseInteractionListener;

            categoryTv.setText(item.getName());

            itemView.setOnClickListener(view -> listener.onCategoryClicked(item));
        }
    }

}
