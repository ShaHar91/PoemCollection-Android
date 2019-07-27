package com.shahar91.poems.ui.home.poem.redux;

import com.shahar91.poems.data.models.Poem;
import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.annotations.ActionCreator;

@ActionCreator
public interface PoemActions {
    String RESET = "Poem_RESET";
    String SET_POEM = "Poem_SET_POEM";

    @ActionCreator.Action(RESET)
    Action reset();

    @ActionCreator.Action(SET_POEM)
    Action setPoem(Poem poem);
}
