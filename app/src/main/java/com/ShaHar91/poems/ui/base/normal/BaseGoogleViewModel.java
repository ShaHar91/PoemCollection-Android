package com.shahar91.poems.ui.base.normal;

import androidx.lifecycle.ViewModel;

import java.util.regex.Pattern;

import io.reactivex.disposables.CompositeDisposable;

/**
 * This is the ViewModel that will be used with every Activity
 */
public abstract class BaseGoogleViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public boolean checkDataValidity(String inputText, Pattern pattern) {
        return inputText.trim().isEmpty() || !pattern.matcher(inputText).matches();
    }
}
