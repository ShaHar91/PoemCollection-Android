package com.shahar91.poemcollection.ui.landing;

import android.util.Log;

import com.shahar91.poemcollection.ui.base.normal.BaseGoogleMobileActivity;
import com.shahar91.poemcollection.ui.base.normal.BaseGoogleViewModel;

import javax.inject.Inject;

public class LandingViewModel  extends BaseGoogleViewModel {
    @Inject
    LandingViewModel(){
        Log.d("TESTING_SOMETHING", "LandingViewModel");
    }
}
