package com.shahar91.poems.ui.home.categories.redux;

import com.shahar91.poems.data.models.Category;
import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.annotations.ActionCreator;

import java.util.List;

@ActionCreator
public interface CategoryActions {
    String RESET = "Categories_RESET";
    String SET_CATEGORY_LIST = "Categories_SET_CATEGORY_LIST";

    @ActionCreator.Action(RESET)
    Action reset();

    @ActionCreator.Action(SET_CATEGORY_LIST)
    Action setCategoryList(List<Category> categoryList);
}
