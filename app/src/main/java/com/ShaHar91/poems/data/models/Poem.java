package com.shahar91.poems.data.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Poem extends RealmObject {
    @PrimaryKey
    private String _id;
    private String title;
    private String body;
    private User user;
    private RealmList<Category> categories;
    private float averageRating;
    private RealmList<Integer> totalRatingCount;
    private RealmList<Review> shortReviewList;
    private Date createdAt;

    public Poem() {
    }

    public Poem(String _id, String title, String body, User user, RealmList<Category> categories, float averageRating, RealmList<Integer> totalRatingCount, RealmList<Review> shortReviewList, Date createdAt) {
        this._id = _id;
        this.title = title;
        this.body = body;
        this.user = user;
        this.categories = categories;
        this.averageRating = averageRating;
        this.totalRatingCount = totalRatingCount;
        this.shortReviewList = shortReviewList;
        this.createdAt = createdAt;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RealmList<Category> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<Category> categories) {
        this.categories = categories;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public RealmList<Integer> getTotalRatingCount() {
        return totalRatingCount;
    }

    public void setTotalRatingCount(RealmList<Integer> totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public RealmList<Review> getShortReviewList() {
        return shortReviewList;
    }

    public void setShortReviewList(RealmList<Review> shortReviewList) {
        this.shortReviewList = shortReviewList;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
