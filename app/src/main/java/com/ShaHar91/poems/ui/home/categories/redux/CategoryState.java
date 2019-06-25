package com.shahar91.poems.ui.home.categories.redux;

import com.google.auto.value.AutoValue;
import com.shahar91.poems.data.models.Category;

import java.util.List;

import javax.annotation.Nullable;

@AutoValue
public abstract class CategoryState {
    @Nullable
    public abstract List<Category> categoryList();

    public static Builder builder() {
        return new AutoValue_CategoryState.Builder();
    }

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setCategoryList(List<Category> categoryList);

        public abstract CategoryState build();
    }
}
