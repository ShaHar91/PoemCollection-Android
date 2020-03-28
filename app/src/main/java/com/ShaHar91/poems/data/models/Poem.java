package com.shahar91.poems.data.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Poem extends RealmObject {
    @PrimaryKey
    private int poemId;
    private String title;
    private String body;
    private User writer;
    private RealmList<Review> reviews;
    private RealmList<Category> categories;

    public Poem() {
    }

    public Poem(int poemId, String title, String body, User writer, RealmList<Review> reviews, RealmList<Category> categories) {
        this.poemId = poemId;
        this.title = title;
        this.body = body;
        this.writer = writer;
        this.reviews = reviews;
        this.categories = categories;
    }

    public int getPoemId() {
        return poemId;
    }

    public void setPoemId(int poemId) {
        this.poemId = poemId;
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

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public RealmList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(RealmList<Review> reviews) {
        this.reviews = reviews;
    }

    public RealmList<Category> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<Category> categories) {
        this.categories = categories;
    }
}
