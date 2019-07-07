package com.shahar91.poems.data.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Category implements Serializable {
    private int id;
    private String name;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "id: " + id + "\n name: " + name;
    }
}
