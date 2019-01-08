package com.sherlockcodes.libraryDemo.pojo;

import java.util.ArrayList;

public class Author {
    long id;
    String name;
    ArrayList<Long> articles;

    public Author() {
    }

    public Author(long id) {
        this.id = id;
        this.articles = new ArrayList<Long>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Long> getArticles() {
        return articles;
    }

    public void addArticles(Long articles) {
        this.articles.add(articles);
    }

    public void removeArticles(Long article) {
        this.getArticles().remove(article);

    }
}
