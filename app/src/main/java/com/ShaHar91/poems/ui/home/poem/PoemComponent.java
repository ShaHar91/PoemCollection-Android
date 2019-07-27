package com.shahar91.poems.ui.home.poem;

import com.shahar91.poems.injection.ApplicationComponent;
import com.shahar91.poems.injection.scope.FragmentScope;
import com.shahar91.poems.ui.base.normal.BaseGoogleComponent;

import dagger.Component;

@FragmentScope
@Component(dependencies = ApplicationComponent.class)
public interface PoemComponent extends BaseGoogleComponent<PoemViewModel> {
    void inject(PoemFragment poemFragment);
}
