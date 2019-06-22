package com.shahar91.poems.ui.landing;

import com.shahar91.poems.injection.ApplicationComponent;
import com.shahar91.poems.injection.scope.ActivityScope;
import com.shahar91.poems.ui.base.normal.BaseGoogleComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface LandingComponent extends BaseGoogleComponent<LandingViewModel> {
    void inject(LandingActivity landingActivity);
}
