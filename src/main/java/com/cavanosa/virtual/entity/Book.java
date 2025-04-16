package com.cavanosa.virtual.entity;

public class Book {
    private String id;
    private String title;
    private String author;

    // Constructor
    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
}
