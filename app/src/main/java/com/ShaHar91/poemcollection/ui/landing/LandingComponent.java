package com.shahar91.poemcollection.ui.landing;

import com.shahar91.poemcollection.injection.ApplicationComponent;
import com.shahar91.poemcollection.injection.scope.ActivityScope;
import com.shahar91.poemcollection.ui.base.normal.BaseGoogleComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface LandingComponent extends BaseGoogleComponent<LandingViewModel> {
    void inject(LandingActivity landingActivity);
}
