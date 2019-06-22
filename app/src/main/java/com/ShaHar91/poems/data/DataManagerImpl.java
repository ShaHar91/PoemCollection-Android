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
        return  db.collection("categories").get();
    }

//    db.collection("users")
//        .get()
//        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.d(TAG, document.getId() + " => " + document.getData());
//                    }
//                } else {
//                    Log.w(TAG, "Error getting documents.", task.getException());
//                }
//            }
//        });
}
