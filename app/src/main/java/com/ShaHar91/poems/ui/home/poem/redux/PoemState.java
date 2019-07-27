package com.shahar91.poems.ui.home.poem.redux;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.shahar91.poems.data.models.Poem;

@AutoValue
public abstract class PoemState {
    @Nullable
    public abstract Poem poem();

    public static Builder builder() { return new AutoValue_PoemState.Builder(); }

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public abstract static class Builder{
        public abstract Builder setPoem(Poem poem);

        public abstract PoemState build();
    }
}
