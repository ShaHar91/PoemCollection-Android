package com.shahar91.poems.injection.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.shahar91.poems.injection.scope.ApplicationScope;
import com.shahar91.poems.ui.landing.LandingViewModel;
import com.shahar91.poems.ui.main.MainViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {
    @ApplicationScope
    @Provides
    ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory) {
        return factory;
    }

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    ViewModel bindMainViewModel(MainViewModel mainViewModel) {
        return mainViewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(LandingViewModel.class)
    ViewModel bindLandingViewModel(LandingViewModel landingViewModel) {
        return landingViewModel;
    }
}
