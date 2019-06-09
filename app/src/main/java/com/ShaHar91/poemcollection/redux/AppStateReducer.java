package com.shahar91.poemcollection.redux;

import com.shahar91.poemcollection.redux.state.ErrorState;
import com.google.auto.value.AutoValue;
import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.Reducer;

@AutoValue
public abstract class AppStateReducer implements Reducer<AppState> {
//    abstract Reducer<ErrorState> errorStateReducer();

    @Override
    public AppState reduce(AppState state, Action action) {
//        ErrorState errorState = null;
//
//        if (state != null && !action.type.equals(AppStateActions.RESET)){
//            errorState = state.errorState();
//        }
//
//        ErrorState errorStateNext = errorStateReducer().reduce(errorState, action);
//
//        if (state != null && errorState == errorStateNext){
//            return state;
//        }else{
//            return new AutoValue_AppState(errorStateNext);
//        }

        return state;
    }

    public static Builder builder() {
        return new AutoValue_AppStateReducer.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder{
//        public abstract Builder errorStateReducer(Reducer<ErrorState> errorStateReducer);

        public abstract AppStateReducer build();
    }
}
