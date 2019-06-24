package com.shahar91.poems.ui.home.categories;

import android.util.Log;

import com.shahar91.poems.data.DataManager;
import com.shahar91.poems.ui.base.normal.BaseGoogleViewModel;

import javax.inject.Inject;

public class CategoryViewModel extends BaseGoogleViewModel {
    private final DataManager dataManager;

    @Inject
    public CategoryViewModel(DataManager dataManager) {
        this.dataManager = dataManager;

        getAllCategories();
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
