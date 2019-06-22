package com.shahar91.poems.ui.main;

import android.util.Log;

import com.shahar91.poems.data.DataManager;
import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;

import javax.inject.Inject;

public class MainViewModel extends BaseGoogleViewModel {
    private final DataManager dataManager;

    @Inject
    MainViewModel(DataManager dataManager) {
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
