package com.shahar91.poems.data.models;

public class New_Category {
    public static final String ID = "id";
    public static final String NAME = "name";

    private int categoryId;
    private String name;

    public New_Category() {
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
