package com.shahar91.poems.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import javax.inject.Inject;

public class DataManagerImpl implements DataManager {
    private final FirebaseFirestore db;

    @Inject
    public DataManagerImpl(FirebaseFirestore db) {
        this.db = db;
    }

    @Override
    public Task<QuerySnapshot> getCategories() {
        return db.collection("categories").get();
    }
}
