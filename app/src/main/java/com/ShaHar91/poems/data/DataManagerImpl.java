package com.shahar91.poems.data;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.data.models.Poem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

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
    public Observable<List<Category>> getCategories() {
        return Observable.create(emitter -> {
            ListenerRegistration listenerRegistration = db.collection(BASE_CATEGORIES).orderBy(Category.NAME).addSnapshotListener((querySnapshot, exception) -> {
                if (exception != null) {
                    emitter.onError(exception);
                    return;
                }

                if (querySnapshot != null) {
                    emitter.onNext(querySnapshot.toObjects(Category.class));
                }
            });

            emitter.setCancellable(listenerRegistration::remove);
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// PoemsPerCategory
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Observable<List<Poem>> getPoemsPerCategories(String categoryId) {
        return Observable.create(emitter -> {
            db.collection(BASE_CATEGORIES).document(categoryId).get().addOnSuccessListener(queryDocumentSnapshots -> {
                ListenerRegistration listenerRegistration =
                        db.collectionGroup(BASE_POEMS)
                                .whereArrayContains(BASE_CATEGORIES, queryDocumentSnapshots.getReference())
                                .orderBy(Poem.TITLE)
                                .addSnapshotListener((queryDocumentSnapshots1, e) -> {
                                    if (e != null) {
                                        emitter.onError(e);
                                        return;
                                    }

                                    if (queryDocumentSnapshots1 != null) {
                                        emitter.onNext(queryDocumentSnapshots1.toObjects(Poem.class));
                                    }
                                });
                emitter.setCancellable(listenerRegistration::remove);
            });
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Poem
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Observable<Poem> getPoem(String poemId) {
        return Observable.create(emitter -> {
            ListenerRegistration listenerRegistration = db.collection(BASE_POEMS).document(poemId).addSnapshotListener((documentSnapshot, e) -> {
                if (e != null) {
                    emitter.onError(e);
                    return;
                }

                if (documentSnapshot != null) {
                    emitter.onNext(documentSnapshot.toObject(Poem.class));
                }
            });

            emitter.setCancellable(listenerRegistration::remove);
        });
    }
}