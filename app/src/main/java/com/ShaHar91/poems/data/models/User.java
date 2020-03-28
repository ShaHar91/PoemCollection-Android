package com.shahar91.poems.data.models;

import androidx.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    @PrimaryKey
    private int userId;
    private String email;
    private String fullName;
    @Nullable
    private String pictureUrl;

    public User() {
    }

    public User(int userId, String email, String fullName, @Nullable String pictureUrl) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.pictureUrl = pictureUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public @Nullable String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(@Nullable String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
