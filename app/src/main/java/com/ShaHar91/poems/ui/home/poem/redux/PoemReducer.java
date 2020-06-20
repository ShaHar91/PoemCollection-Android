package com.shahar91.poems.ui.home.poem.redux;

import androidx.annotation.Nullable;

import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.data.models.Review;
import com.yheriatovych.reductor.Reducer;
import com.yheriatovych.reductor.annotations.AutoReducer;

import java.util.List;

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

    @AutoReducer.Action(value = PoemActions.SET_REVIEWS,from = PoemActions.class)
    PoemState setReviews(PoemState state, List<Review> reviews){
        return state.toBuilder().setReviews(reviews).build();
    }

    @AutoReducer.Action(value = PoemActions.SET_OWN_REVIEW, from = PoemActions.class)
    PoemState setOwnReview(PoemState state, Review review) {
        return state.toBuilder().setOwnReview(review).build();
    }

    public static PoemReducer create() { return new PoemReducerImpl(); }
}
