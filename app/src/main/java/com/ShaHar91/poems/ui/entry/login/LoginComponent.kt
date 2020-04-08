package com.shahar91.poems.ui.entry.login

import com.shahar91.poems.injection.ApplicationComponent
import com.shahar91.poems.injection.scope.ActivityScope
import com.shahar91.poems.ui.base.normal.BaseGoogleComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface LoginComponent : BaseGoogleComponent<LoginViewModel> {
    fun inject(loginFragment: LoginFragment)
}