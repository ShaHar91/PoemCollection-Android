package com.shahar91.poems.data.models;

public class New_User {
    public static final String ID = "id";
    public static final String EMAIL = "email";
    public static final String FULL_NAME = "fullName";
    public static final String PICTURE_URL = "pictureUrl";

    private int userId;
    private String email;
    private String fullName;
    private String pictureUrl;

    public New_User() {
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
