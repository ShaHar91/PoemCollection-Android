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


public class CategoryAdapter extends BaseAdapter<Category, CategoryAdapter.CategoryInteractionListener, BaseViewHolder<Category, CategoryAdapter.CategoryInteractionListener>> {
    private final CategoryInteractionListener listener;

    public interface CategoryInteractionListener {
        void onCategoryClicked(Category category);
    }

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
    public void onBindViewHolder(@NonNull BaseViewHolder<Category, CategoryInteractionListener> holder, int position) {
        holder.bind(position, getItem(position), listener);
    }

    static class CategoryViewHolder extends BaseViewHolder<Category, CategoryInteractionListener> {
        @BindView(R.id.categoryTv)
        TextView categoryTv;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bind(int position, Category item, CategoryInteractionListener baseInteractionListener) {
            categoryTv.setText(item.getName());

            itemView.setOnClickListener(view -> baseInteractionListener.onCategoryClicked(item));
        }
    }

}
