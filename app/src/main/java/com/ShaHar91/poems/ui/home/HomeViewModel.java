package com.shahar91.poems.ui.home;

import android.util.Log;

import com.shahar91.poems.data.DataManager;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;

import javax.inject.Inject;

public class HomeViewModel extends BaseGoogleViewModel {
    private final DataManager dataManager;

    @Inject
    HomeViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getAllCategories() {
        dataManager.getCategories().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("TaskSuccessful", "getAllCategories: ");
            } else {

            }
        });
    }
}
