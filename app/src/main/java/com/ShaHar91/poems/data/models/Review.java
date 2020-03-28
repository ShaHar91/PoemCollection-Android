package com.shahar91.poems.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Review extends RealmObject {
    @PrimaryKey
    private int reviewId;
    private float rating;
    private String body;
    private long createdAt;
    private User user;

    public Review() {
    }

    public Review(int reviewId, float rating, String body, long createdAt, User user) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.body = body;
        this.createdAt = createdAt;
        this.user = user;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
