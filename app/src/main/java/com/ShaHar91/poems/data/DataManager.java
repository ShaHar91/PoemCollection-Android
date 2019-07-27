package com.shahar91.poems.data;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

public interface DataManager {
    Query getCategoriesQuery();

    DocumentReference getPoemByReference(String poemId);

    Query getPoemsPerCategoryQuery(DocumentReference categoryReference);

    DocumentReference getCategoryReference(String categoryId);
}
