package com.shahar91.poemcollection.ui.main;

import com.shahar91.poemcollection.injection.ApplicationComponent;
import com.shahar91.poemcollection.injection.scope.ActivityScope;
import com.shahar91.poemcollection.ui.base.normal.BaseGoogleComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
interface MainComponent extends BaseGoogleComponent<MainViewModel> {
    void inject(MainActivity mainActivity);
}