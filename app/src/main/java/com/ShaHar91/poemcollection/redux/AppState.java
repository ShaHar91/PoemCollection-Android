package com.shahar91.poemcollection.redux;

import com.shahar91.poemcollection.redux.state.ErrorState;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class AppState {
    public abstract ErrorState errorState();
}
