package com.shahar91.poems.ui.home.poemsPerCategoryList.adapter;

import com.shahar91.poems.ui.base.list.BaseInteractionListener;

public interface PoemsPerCategoryListInteractionListener extends BaseInteractionListener {
    void onPoemClicked(int poemId);
}
