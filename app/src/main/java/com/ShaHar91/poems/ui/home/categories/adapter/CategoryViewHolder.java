package com.shahar91.poems.ui.home.categories.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.shahar91.poems.R;
import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.ui.base.list.BaseInteractionListener;
import com.shahar91.poems.ui.base.list.BaseViewHolder;

import butterknife.BindView;

public class CategoryViewHolder extends BaseViewHolder<Category, BaseInteractionListener> {
    @BindView(R.id.categoryTv)
    TextView categoryTv;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(int position, Category item, BaseInteractionListener listener) {
        categoryTv.setText(item.getName());
    }
}
