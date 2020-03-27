package com.shahar91.poems.data.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class New_Poem extends RealmObject {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String BODY = "body";
    public static final String WRITER = "writer";
    public static final String REVIEWS = "reviews";
    public static final String CATEGORIES = "categories";

    @PrimaryKey
    private int poemId;
    private String title;
    private String body;
    private New_User writer;
    private RealmList<New_Review> reviews;
    private RealmList<New_Category> categories;

    public New_Poem() {
    }

    public New_Poem(int poemId, String title, String body, New_User writer, RealmList<New_Review> reviews, RealmList<New_Category> categories) {
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

    public New_User getWriter() {
        return writer;
    }

    public void setWriter(New_User writer) {
        this.writer = writer;
    }

    public RealmList<New_Review> getReviews() {
        return reviews;
    }

    public void setReviews(RealmList<New_Review> reviews) {
        this.reviews = reviews;
    }

    public RealmList<New_Category> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<New_Category> categories) {
        this.categories = categories;
    }
}
