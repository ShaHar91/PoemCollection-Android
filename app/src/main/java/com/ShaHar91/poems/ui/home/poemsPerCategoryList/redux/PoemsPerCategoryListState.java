package com.shahar91.poems.ui.home.poemsPerCategoryList.redux;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.shahar91.poems.data.models.PoemsPerCategory;

import java.util.List;

@AutoValue
public abstract class PoemsPerCategoryListState {
    @Nullable
    public abstract List<PoemsPerCategory> poemsPerCategoryList();

    public static Builder builder() {
        return new AutoValue_PoemsPerCategoryListState.Builder();
    }

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setPoemsPerCategoryList(List<PoemsPerCategory> poemsPerCategoryList);

        public abstract PoemsPerCategoryListState build();
    }
}
