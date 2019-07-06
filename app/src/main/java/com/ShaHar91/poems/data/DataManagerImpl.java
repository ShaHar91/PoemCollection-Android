package com.shahar91.poems.data;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Inject;

public class DataManagerImpl implements DataManager {
    private final FirebaseFirestore db;

    @Inject
    DataManagerImpl(FirebaseFirestore db) {
        this.db = db;
    }


    // Categories
    @Override
    public CollectionReference getCategoriesByReference() {
        return db.collection("categories");
    }


    // PoemsPerCategory
    @Override
    public CollectionReference getPoemsPerCategoryByReference(int categoryId) {
        return db.collection("per_category/" + categoryId + "/poems");
    }
}
