package com.shahar91.poems.redux.state;

import com.google.auto.value.AutoValue;
import com.shahar91.poems.ui.home.categories.redux.CategoryState;
import com.yheriatovych.reductor.annotations.CombinedState;

@CombinedState
@AutoValue
public abstract class ViewState {
    public abstract CategoryState categoryState();
}
