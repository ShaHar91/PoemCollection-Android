package com.shahar91.poems.data.models;

import androidx.annotation.NonNull;

public class Category {
    private int id;
    private String name;
//
//    public Category() {
//    }


    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "id: " + id + "\n name: " + name;
    }
}
