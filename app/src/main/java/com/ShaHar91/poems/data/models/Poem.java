package com.shahar91.poems.data.models;

import java.util.List;

public class Poem {
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String POEM = "poem";
    public static final String WRITER = "writer";

    private String id;
    private String title;
    private String poem;
    private String writer;
//    private List<Integer> categories;

    public Poem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        //TODO: in case it's needed, replace \\n by \n
        this.poem = poem.replace("\\n", "\n");
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

//    public List<Integer> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<Integer> categories) {
//        this.categories = categories;
//    }
}
