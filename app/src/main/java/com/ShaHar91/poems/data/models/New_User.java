package com.shahar91.poems.data.models;

import androidx.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class New_User extends RealmObject {
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String FULL_NAME = "fullName";
    public static final String PICTURE_URL = "pictureUrl";

    @PrimaryKey
    private int userId;
    private String email;
    private String fullName;
    @Nullable
    private String pictureUrl;

    public New_User() {
    }

    public New_User(int userId, String email, String fullName, @Nullable String pictureUrl) {
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
