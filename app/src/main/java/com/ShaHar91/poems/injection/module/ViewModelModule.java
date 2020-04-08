package com.shahar91.poems.injection.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.shahar91.poems.injection.scope.ApplicationScope;
import com.shahar91.poems.ui.add.AddPoemViewModel;
import com.shahar91.poems.ui.entry.EntryViewModel;
import com.shahar91.poems.ui.entry.login.LoginViewModel;
import com.shahar91.poems.ui.entry.register.RegisterViewModel;
import com.shahar91.poems.ui.home.HomeViewModel;
import com.shahar91.poems.ui.home.categories.CategoryViewModel;
import com.shahar91.poems.ui.home.poem.PoemViewModel;
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
    @ViewModelKey(AddPoemViewModel.class)
    ViewModel bindAddPoemViewModel(AddPoemViewModel addPoemViewModel) {
        return addPoemViewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(EntryViewModel.class)
    ViewModel bindEntryViewModel(EntryViewModel entryViewModel) {
        return entryViewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    ViewModel bindLoginViewModel(LoginViewModel loginViewModel) {
        return loginViewModel;
    }

    @Provides
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    ViewModel bindRegisterViewModel(RegisterViewModel registerViewModel) {
        return registerViewModel;
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

    @Provides
    @IntoMap
    @ViewModelKey(PoemViewModel.class)
    ViewModel bindPoemViewModel(PoemViewModel poemViewModel) {
        return poemViewModel;
    }
}
