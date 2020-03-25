package com.shahar91.poems.data.models;

import java.util.List;

public class New_Poem {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String BODY = "body";
    public static final String WRITER = "writer";
    public static final String REVIEWS = "reviews";
    public static final String CATEGORIES = "categories";

    private int poemId;
    private String title;
    private String body;
    private New_User writer;
    private List<New_Review> reviews;
    private List<New_Category> categories;

    public New_Poem() {
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

    public List<New_Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<New_Review> reviews) {
        this.reviews = reviews;
    }

    public List<New_Category> getCategories() {
        return categories;
    }

    public void setCategories(List<New_Category> categories) {
        this.categories = categories;
    }
}
