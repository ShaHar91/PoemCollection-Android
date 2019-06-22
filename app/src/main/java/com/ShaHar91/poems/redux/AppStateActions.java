package com.shahar91.poems.redux;

import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.annotations.ActionCreator;

@ActionCreator
public interface AppStateActions {
    String RESET = "AppState_RESET";

    @ActionCreator.Action(RESET)
    Action reset();
}
