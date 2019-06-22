package com.shahar91.poems.ui.base.customView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * This will primarily be used for custom views, I think? O.o
 */
public abstract class BaseViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public abstract void viewCreated();

    public void viewDestroyed() {
        compositeDisposable.dispose();
    }

    public abstract void resetState();

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}