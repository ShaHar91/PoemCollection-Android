package com.shahar91.poems.ui.add

import com.shahar91.poems.injection.ApplicationComponent
import com.shahar91.poems.injection.scope.ActivityScope
import com.shahar91.poems.ui.base.BaseGoogleComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface AddPoemComponent : BaseGoogleComponent<AddPoemViewModel> {
    fun inject(addPoemActivity: AddPoemActivity)
}