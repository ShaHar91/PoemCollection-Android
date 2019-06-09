package com.shahar91.poemcollection.injection;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.shahar91.poemcollection.injection.module.ApplicationModule;
import com.shahar91.poemcollection.injection.module.ReduxModule;
import com.shahar91.poemcollection.injection.module.ViewModelModule;
import com.shahar91.poemcollection.injection.qualifier.ApplicationContext;
import com.shahar91.poemcollection.injection.scope.ApplicationScope;
import com.shahar91.poemcollection.redux.AppState;
import com.yheriatovych.reductor.Store;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class,
        ReduxModule.class,
        ViewModelModule.class})
public interface ApplicationComponent {
    @ApplicationContext
    Context appContext();

    // ViewModelModule
    ViewModelProvider.Factory viewModelFactory();

    Store<AppState> store();
}
