package com.shahar91.poems.ui.home.poem

import com.shahar91.poems.injection.ApplicationComponent
import com.shahar91.poems.injection.scope.FragmentScope
import com.shahar91.poems.ui.base.BaseGoogleComponent
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class])
interface PoemComponent : BaseGoogleComponent<PoemViewModel> {
    fun inject(poemFragment: PoemFragment?)
}