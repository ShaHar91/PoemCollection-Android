package com.shahar91.poems.ui.landing

import com.shahar91.poems.redux.AppState
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel
import com.yheriatovych.reductor.Store
import javax.inject.Inject

class LandingViewModel @Inject internal constructor(store: Store<AppState>) :
    BaseGoogleViewModel(store)