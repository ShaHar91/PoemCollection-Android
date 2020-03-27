package com.shahar91.poems.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class New_Review extends RealmObject {
    public static final String ID = "id";
    public static final String BODY = "body";
    public static final String RATING = "rating";
    public static final String CREATED_AT = "created_at";
    public static final String USER = "user";

    @PrimaryKey
    private int reviewId;
    private float rating;
    private String body;
    private long createdAt;
    private New_User user;

    public New_Review() {
    }

    public New_Review(int reviewId, float rating, String body, long createdAt, New_User user) {
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

    public New_User getUser() {
        return user;
    }

    public void setUser(New_User user) {
        this.user = user;
    }
}
