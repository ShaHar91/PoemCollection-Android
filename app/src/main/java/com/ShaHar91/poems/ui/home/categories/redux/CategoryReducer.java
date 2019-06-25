package com.shahar91.poems.ui.home.categories.redux;

import com.shahar91.poems.data.models.Category;
import com.yheriatovych.reductor.Reducer;
import com.yheriatovych.reductor.annotations.AutoReducer;

import java.util.List;

@AutoReducer
public abstract class CategoryReducer implements Reducer<CategoryState> {
    @AutoReducer.InitialState
    CategoryState initialState() {
        return CategoryState.builder()
                .setCategoryList(null).build();
    }

    @AutoReducer.Action(value = CategoryActions.RESET, from = CategoryActions.class)
    CategoryState reset(CategoryState state) {
        return initialState();
    }

    @AutoReducer.Action(value = CategoryActions.SET_CATEGORY_LIST, from = CategoryActions.class)
    CategoryState setCategoryList(CategoryState state, List<Category> categoryList) {
        return state.toBuilder().setCategoryList(categoryList).build();
    }

    public static CategoryReducer create() {
        return new CategoryReducerImpl();
    }
}
