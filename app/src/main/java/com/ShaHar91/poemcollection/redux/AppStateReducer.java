package com.shahar91.poemcollection.redux;

import com.google.auto.value.AutoValue;
import com.shahar91.poemcollection.redux.state.ErrorState;
import com.shahar91.poemcollection.redux.state.ViewState;
import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.Reducer;

@AutoValue
public abstract class AppStateReducer implements Reducer<AppState> {
    abstract Reducer<ViewState> viewStateReducer();

    abstract Reducer<ErrorState> errorStateReducer();

    @Override
    public AppState reduce(AppState state, Action action) {
        ViewState viewState = null;
        ErrorState errorState = null;

        if (state != null && !action.type.equals(AppStateActions.RESET)) {
            viewState = state.viewState();
            errorState = state.errorState();
        }

        ViewState viewStateNext = viewStateReducer().reduce(viewState, action);
        ErrorState errorStateNext = errorStateReducer().reduce(errorState, action);

        if (state != null
                && errorState == errorStateNext
                && viewState == viewStateNext) {
            return state;
        } else {
            return new AutoValue_AppState(viewStateNext, errorStateNext);
        }
    }

    public static Builder builder() {
        return new AutoValue_AppStateReducer.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder viewStateReducer(Reducer<ViewState> viewStateReducer);

        public abstract Builder errorStateReducer(Reducer<ErrorState> errorStateReducer);

        public abstract AppStateReducer build();
    }
}
