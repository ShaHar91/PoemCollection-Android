package com.shahar91.poems.ui.home.poemsPerCategoryList;

import com.shahar91.poems.injection.ApplicationComponent;
import com.shahar91.poems.injection.scope.FragmentScope;
import com.shahar91.poems.ui.base.normal.BaseGoogleComponent;

import dagger.Component;

@FragmentScope
@Component(dependencies = ApplicationComponent.class)
public interface PoemsPerCategoryListComponent extends BaseGoogleComponent<PoemsPerCategoryListViewModel> {
    void inject(PoemsPerCategoryListFragment poemsPerCategoryListFragment);
}
