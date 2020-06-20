package com.shahar91.poems.ui.home.poem.redux;

import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.data.models.Review;
import com.yheriatovych.reductor.Action;
import com.yheriatovych.reductor.annotations.ActionCreator;

import java.util.List;

@ActionCreator
public interface PoemActions {
    String RESET = "Poem_RESET";
    String SET_POEM = "Poem_SET_POEM";
    String SET_REVIEWS = "Poem_SET_REVIEWS";
    String SET_OWN_REVIEW = "Poem_SET_OWN_REVIEW";

    @ActionCreator.Action(RESET)
    Action reset();

    @ActionCreator.Action(SET_POEM)
    Action setPoem(Poem poem);

    @ActionCreator.Action(SET_REVIEWS)
    Action setReviews(List<Review> reviews);

    @ActionCreator.Action(SET_OWN_REVIEW)
    Action setOwnReview(Review review);
}
