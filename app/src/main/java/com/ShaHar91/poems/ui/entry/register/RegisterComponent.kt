package com.shahar91.poems.ui.entry.register

import com.shahar91.poems.injection.ApplicationComponent
import com.shahar91.poems.injection.scope.ActivityScope
import com.shahar91.poems.ui.base.normal.BaseGoogleComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface RegisterComponent : BaseGoogleComponent<RegisterViewModel>{
    fun inject(registerFragment: RegisterFragment)
}