package com.shahar91.poems.ui.landing;

import android.util.Log;

import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;

import javax.inject.Inject;

public class LandingViewModel  extends BaseGoogleViewModel {
    @Inject
    LandingViewModel(){
        Log.d("TESTING_SOMETHING", "LandingViewModel");
    }
}
