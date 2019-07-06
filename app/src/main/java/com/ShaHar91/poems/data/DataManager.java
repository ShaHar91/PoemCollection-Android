package com.shahar91.poems.data;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;

public interface DataManager {
    abstract Task<QuerySnapshot> getCategories();
    abstract CollectionReference getCategoriesByReference();
}
