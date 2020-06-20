package com.shahar91.poems.data.models;

import androidx.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    @PrimaryKey
    private String _id;
    private String email;
    private String username;
    @Nullable
    private String pictureUrl;

    public User() {
    }

    public User(String _id, String email, String username, @Nullable String pictureUrl) {
        this._id = _id;
        this.email = email;
        this.username = username;
        this.pictureUrl = pictureUrl;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Nullable
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(@Nullable String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
