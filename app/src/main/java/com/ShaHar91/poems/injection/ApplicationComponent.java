package com.shahar91.poems.injection;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.shahar91.poems.injection.module.ApplicationModule;
import com.shahar91.poems.injection.module.ViewModelModule;
import com.shahar91.poems.injection.qualifier.ApplicationContext;
import com.shahar91.poems.injection.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class,
        ViewModelModule.class})
public interface ApplicationComponent {
    @ApplicationContext
    Context appContext();

    // ViewModelModule
    ViewModelProvider.Factory viewModelFactory();
}
