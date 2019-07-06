package com.shahar91.poems.ui.landing;

import com.shahar91.poems.redux.AppState;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;
import com.yheriatovych.reductor.Store;

import javax.inject.Inject;

public class LandingViewModel extends BaseGoogleViewModel {
    @Inject
    LandingViewModel(Store<AppState> store) {
        super(store);
    }
}
