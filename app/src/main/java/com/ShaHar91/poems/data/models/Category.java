package com.shahar91.poems.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Category extends RealmObject {
    @PrimaryKey
    private int categoryId;
    private String name;

    public Category() {
    }

    public Category(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
