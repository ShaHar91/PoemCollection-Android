package com.shahar91.poems.injection.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.shahar91.poems.injection.scope.ApplicationScope;
import com.shahar91.poems.ui.home.HomeViewModel;
import com.shahar91.poems.ui.home.categories.CategoryViewModel;
import com.shahar91.poems.ui.home.poemsPerCategoryList.PoemsPerCategoryListViewModel;
import com.shahar91.poems.ui.landing.LandingViewModel;

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
    @ViewModelKey(HomeViewModel.class)
    ViewModel bindMainViewModel(HomeViewModel homeViewModel) {
        return homeViewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(LandingViewModel.class)
    ViewModel bindLandingViewModel(LandingViewModel landingViewModel) {
        return landingViewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    ViewModel bindCategoryViewModel(CategoryViewModel categoryViewModel) {
        return categoryViewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(PoemsPerCategoryListViewModel.class)
    ViewModel bindPoemsPerCategoryListViewModel(PoemsPerCategoryListViewModel poemsPerCategoryListViewModel) {
        return poemsPerCategoryListViewModel;
    }
}
