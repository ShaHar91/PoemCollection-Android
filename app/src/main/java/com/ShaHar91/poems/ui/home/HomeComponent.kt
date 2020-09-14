package com.shahar91.poems.ui.home

import com.shahar91.poems.injection.ApplicationComponent
import com.shahar91.poems.injection.scope.ActivityScope
import com.shahar91.poems.ui.base.normal.BaseGoogleComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface HomeComponent : BaseGoogleComponent<HomeViewModel> {
    fun inject(homeActivity: HomeActivity)
}