package com.ShaHar91.poemcollection.injection.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ShaHar91.poemcollection.injection.scope.ApplicationScope;
import com.ShaHar91.poemcollection.ui.main.MainViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {
    @ApplicationScope
    @Provides
    ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory) { return factory; }

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    ViewModel bindMainViewModel(MainViewModel mainViewModel){
        return mainViewModel;
    }
}
