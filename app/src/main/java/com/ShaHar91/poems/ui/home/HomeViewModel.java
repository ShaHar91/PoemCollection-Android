package com.shahar91.poems.ui.home;

import com.shahar91.poems.redux.AppState;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;
import com.yheriatovych.reductor.Store;

import javax.inject.Inject;

public class HomeViewModel extends BaseGoogleViewModel {
    @Inject
    HomeViewModel(Store<AppState> store) {
        super(store);
    }
}
