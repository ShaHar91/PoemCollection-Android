package com.shahar91.poems.data;

import com.google.firebase.firestore.CollectionReference;

public interface DataManager {
    CollectionReference getCategoriesByReference();

    CollectionReference getPoemsPerCategoryByReference(int categoryId);
}
