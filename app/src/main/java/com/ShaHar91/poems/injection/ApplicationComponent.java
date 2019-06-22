package com.shahar91.poems.injection;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.FirebaseFirestore;
import com.shahar91.poems.data.DataManager;
import com.shahar91.poems.injection.module.ApplicationModule;
import com.shahar91.poems.injection.module.NetworkModule;
import com.shahar91.poems.injection.module.ReduxModule;
import com.shahar91.poems.injection.module.ViewModelModule;
import com.shahar91.poems.injection.qualifier.ApplicationContext;
import com.shahar91.poems.injection.scope.ApplicationScope;
import com.shahar91.poems.redux.AppState;
import com.yheriatovych.reductor.Store;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class,
        NetworkModule.class,
        ReduxModule.class,
        ViewModelModule.class})
public interface ApplicationComponent {
    @ApplicationContext
    Context appContext();

    // ViewModelModule
    ViewModelProvider.Factory viewModelFactory();

    Store<AppState> store();

    FirebaseFirestore firebaseFirestore();

    DataManager dataManager();
}
