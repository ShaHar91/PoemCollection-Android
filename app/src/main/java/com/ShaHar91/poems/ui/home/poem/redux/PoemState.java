package com.shahar91.poems.ui.home.poem.redux;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.data.models.Review;

import java.util.List;

@AutoValue
public abstract class PoemState {
    @Nullable
    public abstract Poem poem();

    @Nullable
    public abstract List<Review> reviews();

    @Nullable
    public abstract Review ownReview();

    @Nullable
    public abstract Float delayedRating();

    public static Builder builder() { return new AutoValue_PoemState.Builder(); }

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder{
        public abstract Builder setPoem(Poem poem);

        public abstract Builder setReviews(List<Review> reviews);

        public abstract Builder setOwnReview(Review ownReview);

        public abstract Builder setDelayedRating(Float rating);

        public abstract PoemState build();
    }
}
