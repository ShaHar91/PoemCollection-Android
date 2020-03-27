package com.shahar91.poems.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class New_Category extends RealmObject {
    public static final String ID = "id";
    public static final String NAME = "name";

    @PrimaryKey
    private int categoryId;
    private String name;

    public New_Category() {
    }

    public New_Category(int categoryId, String name) {
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
