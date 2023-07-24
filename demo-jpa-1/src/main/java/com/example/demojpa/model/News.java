package com.example.demojpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
//@Table(name = "news")
public class News {

    @Id
    @GeneratedValue
    private Long id;

    //@Column(name = "title")
    private String title;
    private String language;

    public News() {
    }

    public News(String title, String language) {
        this.title = title;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
