package com.shahar91.poems.data;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.data.models.Poem;

import javax.inject.Inject;

public class DataManagerImpl implements DataManager {
    public static final String BASE_CATEGORIES = "categories";
    public static final String BASE_POEMS = "poems";


    private static final String TAG = "Tag";
    private final FirebaseFirestore db;

    @Inject
    DataManagerImpl(FirebaseFirestore db) {
        this.db = db;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Categories
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Query getCategoriesQuery() {
        return db.collection(BASE_CATEGORIES).orderBy(Category.NAME);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// PoemsPerCategory
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public DocumentReference getCategoryReference(String categoryId) {
        return db.collection(BASE_CATEGORIES).document(categoryId);
    }

    @Override
    public Query getPoemsPerCategoryQuery(DocumentReference documentReference) {
        return db.collectionGroup(BASE_POEMS).whereArrayContains("categories", documentReference).orderBy(Poem.TITLE);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Poem
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public DocumentReference getPoemByReference(String poemId) {
        return db.collection(BASE_POEMS).document(poemId);
    }
}