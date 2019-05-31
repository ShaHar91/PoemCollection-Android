package com.ShaHar91.poemcollection.ui.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

//    public abstract void viewCreated();

//    public void viewDestroyed() { compositeDisposable.dispose(); }

//    public abstract void resetState();

    protected void addDisposable(Disposable disposable) { compositeDisposable.add(disposable); }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
