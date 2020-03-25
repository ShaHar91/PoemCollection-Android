package com.shahar91.poems.data;

import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.data.models.Poem;

import java.util.List;

import io.reactivex.Observable;

public interface DataManager {
    Observable<List<Category>> getCategories();

    Observable<Poem> getPoem(String poemId);

    Observable<List<Poem>> getPoemsPerCategories(String categoryId);
}
