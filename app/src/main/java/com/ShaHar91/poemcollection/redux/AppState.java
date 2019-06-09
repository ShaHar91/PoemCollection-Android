package com.shahar91.poemcollection.redux;

import com.shahar91.poemcollection.redux.state.ErrorState;
import com.google.auto.value.AutoValue;
import com.shahar91.poemcollection.redux.state.ViewState;

@AutoValue
public abstract class AppState {
    public abstract ViewState viewState();

    public abstract ErrorState errorState();
}
