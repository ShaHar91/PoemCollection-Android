package com.shahar91.poems.data.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Review extends RealmObject {
    @PrimaryKey
    private String _id;
    private String text;
    private float rating;
    private Date createdAt;
    private Poem poem;
    private User user;

    public Review() {
    }

    public Review(String _id, String text, float rating, Date createdAt, Poem poem, User user) {
        this._id = _id;
        this.text = text;
        this.rating = rating;
        this.createdAt = createdAt;
        this.poem = poem;
        this.user = user;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Poem getPoem() {
        return poem;
    }

    public void setPoem(Poem poem) {
        this.poem = poem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
