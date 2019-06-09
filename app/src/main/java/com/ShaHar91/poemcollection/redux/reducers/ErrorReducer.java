package com.shahar91.poemcollection.redux.reducers;


import com.shahar91.poemcollection.redux.actions.ErrorAction;
import com.shahar91.poemcollection.redux.state.ErrorState;
import com.yheriatovych.reductor.Reducer;
import com.yheriatovych.reductor.annotations.AutoReducer;

@AutoReducer
public abstract class ErrorReducer implements Reducer<ErrorState> {
    @AutoReducer.InitialState
    ErrorState initialState() {
        return ErrorState.builder()
                .build();
    }

    @AutoReducer.Action(value = ErrorAction.RESET, from = ErrorAction.class)
    ErrorState reset(ErrorState state) {
        return initialState();
    }

    @AutoReducer.Action(value = ErrorAction.SET_ERROR, from = ErrorAction.class)
    ErrorState setErrorCode(ErrorState state, String errorCode) {
        return state.toBuilder().errorCode(errorCode).build();
    }

    public static ErrorReducer create() {
        return new ErrorReducerImpl();
    }

}
