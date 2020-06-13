package com.shahar91.poems.ui.add

import com.shahar91.poems.data.DataManager
import com.shahar91.poems.data.models.Poem
import com.shahar91.poems.redux.AppState
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel
import com.yheriatovych.reductor.Store
import javax.inject.Inject

class AddPoemViewModel @Inject internal constructor(store: Store<AppState>, val dataManager: DataManager) :
    BaseGoogleViewModel(store) {
    fun addNewPoem() {
        addDisposable(dataManager.user.subscribe { user ->
            //TODO: get A poem as a json, fill in only the poemTitle and Body and copy that to realm (default categories and user for now)
        })
    }
}