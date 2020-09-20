package com.shahar91.poems.ui.entry.register

import com.shahar91.poems.injection.ApplicationComponent
import com.shahar91.poems.injection.scope.FragmentScope
import com.shahar91.poems.ui.base.BaseGoogleComponent
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface RegisterComponent : BaseGoogleComponent<RegisterViewModel> {
    fun inject(registerFragment: RegisterFragment)
}