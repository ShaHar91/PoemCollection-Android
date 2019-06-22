package com.shahar91.poems.redux.state;

import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ErrorState {

    @Nullable
    public abstract String errorCode();

    public static ErrorState.Builder builder() {
        return new AutoValue_ErrorState.Builder().errorCode(null);
    }

    public abstract ErrorState.Builder toBuilder();

    public static ErrorState create(String errorCode) {
        return builder()
                .errorCode(errorCode)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract ErrorState.Builder errorCode(String errorCode);

        public abstract ErrorState build();
    }
}
