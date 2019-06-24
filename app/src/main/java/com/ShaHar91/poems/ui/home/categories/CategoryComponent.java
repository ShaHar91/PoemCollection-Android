package com.shahar91.poems.ui.home.categories;

import com.shahar91.poems.injection.ApplicationComponent;
import com.shahar91.poems.injection.scope.FragmentScope;
import com.shahar91.poems.ui.base.normal.BaseGoogleComponent;

import dagger.Component;

@FragmentScope
@Component(dependencies = ApplicationComponent.class)
public interface CategoryComponent extends BaseGoogleComponent<CategoryViewModel> {
    void inject(CategoryFragment categoryFragment);
}
