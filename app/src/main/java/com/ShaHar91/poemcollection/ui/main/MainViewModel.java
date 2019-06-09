package com.ShaHar91.poemcollection.ui.main;

import android.util.Log;

import com.ShaHar91.poemcollection.ui.base.normal.BaseGoogleViewModel;

import javax.inject.Inject;

public class MainViewModel extends BaseGoogleViewModel {

    @Inject
    public MainViewModel(){
        Log.d("TESTING_SOMETHING", "MainViewModel: ");
    }
}
