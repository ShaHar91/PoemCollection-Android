package com.shahar91.poems.ui.entry

import com.shahar91.poems.redux.AppState
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel
import com.yheriatovych.reductor.Store
import javax.inject.Inject

class EntryViewModel @Inject internal constructor(store: Store<AppState>) : BaseGoogleViewModel(store)