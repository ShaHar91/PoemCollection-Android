package com.shahar91.poems.ui.base.normal;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.ListenerRegistration;
import com.shahar91.poems.redux.AppState;
import com.yheriatovych.reductor.Store;

import java.util.regex.Pattern;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * This is the ViewModel that will be used with every Activity
 */
public abstract class BaseGoogleViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected final Store<AppState> store;
    protected ListenerRegistration registration;

    public BaseGoogleViewModel(Store<AppState> store) {
        this.store = store;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void stopListeningForChangesInBackend() {
        if (registration != null) {
            registration.remove();
        }
    }

    public boolean checkDataValidity(String inputText, Pattern pattern) {
        return inputText.trim().isEmpty() || !pattern.matcher(inputText).matches();
    }
}
