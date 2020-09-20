package com.shahar91.poems.ui.entry

import com.shahar91.poems.injection.ApplicationComponent
import com.shahar91.poems.injection.scope.ActivityScope
import com.shahar91.poems.ui.base.BaseGoogleComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface EntryComponent : BaseGoogleComponent<EntryViewModel> {
    fun inject(entryActivity: EntryActivity)
}