package com.ShaHar91.poemcollection.injection;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.ShaHar91.poemcollection.injection.module.ApplicationModule;
import com.ShaHar91.poemcollection.injection.module.ViewModelModule;
import com.ShaHar91.poemcollection.injection.qualifier.ApplicationContext;
import com.ShaHar91.poemcollection.injection.scope.ApplicationScope;

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
