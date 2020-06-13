package com.shahar91.poems.data;

import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.data.models.User;

import java.util.List;

import io.reactivex.Observable;

public interface DataManager {
    Observable<User> getUser();

    Observable<List<Category>> getCategories();

    Observable<Poem> getPoem(int poemId);

    Observable<List<Poem>> getPoemsPerCategories(int categoryId);

    Observable<Poem> saveNewPoem();
}
