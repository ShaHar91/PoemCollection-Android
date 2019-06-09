package com.shahar91.poemcollection.redux.actions;

import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.annotations.ActionCreator;

@ActionCreator
public interface ErrorAction {
    String RESET = "ErrorState_RESET";
    String SET_ERROR = "ErrorState_SET_ERROR";

    @ActionCreator.Action(RESET)
    Action reset();

    @ActionCreator.Action(SET_ERROR)
    Action setErrorCode(String errorCode);
}
