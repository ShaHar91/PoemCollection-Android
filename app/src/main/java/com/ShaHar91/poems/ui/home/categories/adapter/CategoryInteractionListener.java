package com.shahar91.poems.ui.home.categories.adapter;

import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.ui.base.list.BaseInteractionListener;

public interface CategoryInteractionListener extends BaseInteractionListener {
    void onCategoryClicked(Category category);
}
