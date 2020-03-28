package com.shahar91.poems.data;

import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.data.models.Poem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;

public class DataManagerImpl implements DataManager {
    private final Realm realm = Realm.getDefaultInstance();

    @Inject
    DataManagerImpl() {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Categories
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Observable<List<Category>> getCategories() {
        return Observable.create(emitter -> {
            RealmResults<Category> results = realm.where(Category.class).findAll();
            emitter.onNext(results.subList(0, results.size()));
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// PoemsPerCategory
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Observable<List<Poem>> getPoemsPerCategories(int categoryId) {
        return Observable.create(emitter -> {
            RealmResults<Poem> results = realm.where(Poem.class).equalTo("categories.categoryId", categoryId).findAll();
            emitter.onNext(results.subList(0, results.size()));
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //// Poem
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Observable<Poem> getPoem(int poemId) {
        return Observable.create(emitter -> {
            Poem poem = realm.where(Poem.class).equalTo("poemId", poemId).findFirst();
            emitter.onNext(poem);
        });
    }
}