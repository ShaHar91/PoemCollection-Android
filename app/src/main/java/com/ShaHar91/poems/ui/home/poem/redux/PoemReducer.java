package com.shahar91.poems.ui.home.poem.redux;

import com.shahar91.poems.data.models.Poem;
import com.yheriatovych.reductor.Reducer;
import com.yheriatovych.reductor.annotations.AutoReducer;

@AutoReducer
public abstract class PoemReducer implements Reducer<PoemState> {
    @AutoReducer.InitialState
    PoemState initialState(){
        return PoemState.builder().build();
    }

    @AutoReducer.Action(value = PoemActions.RESET, from = PoemActions.class)
    PoemState reset(PoemState state) { return initialState(); }

    @AutoReducer.Action(value = PoemActions.SET_POEM,from = PoemActions.class)
    PoemState setPoem(PoemState state, Poem poem){
        return state.toBuilder().setPoem(poem).build();
    }

    public static PoemReducer create() { return new PoemReducerImpl(); }
}
