package com.ShaHar91.poemcollection.ui.base.normal;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

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

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
