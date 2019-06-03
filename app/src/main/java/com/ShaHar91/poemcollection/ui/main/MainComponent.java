package com.ShaHar91.poemcollection.ui.main;

import com.ShaHar91.poemcollection.injection.ApplicationComponent;
import com.ShaHar91.poemcollection.injection.scope.ActivityScope;
import com.ShaHar91.poemcollection.ui.base.BaseGoogleComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
interface MainComponent extends BaseGoogleComponent<MainViewModel> {
    void inject(MainActivity mainActivity);
}