package com.shahar91.poems.redux;

import com.shahar91.poems.redux.state.ErrorState;
import com.google.auto.value.AutoValue;
import com.shahar91.poems.redux.state.ViewState;

@AutoValue
public abstract class AppState {
    public abstract ViewState viewState();

    public abstract ErrorState errorState();
}
