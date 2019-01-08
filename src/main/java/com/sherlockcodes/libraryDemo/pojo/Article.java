package com.sherlockcodes.libraryDemo.pojo;

import java.util.ArrayList;
import java.util.List;

public class Article {

    Long id;
    String desc;
    String text;
    String header;
    Long publishDate;
    List<Long> authors;
    List<String> keyword;
    private Article(ArticleBuilder articleBuilder) {
        this.id = articleBuilder.id;
        this.desc = articleBuilder.desc;
        this.text = articleBuilder.text;
        this.publishDate = articleBuilder.publishDate;
        this.authors = articleBuilder.authors;
        this.keyword = articleBuilder.keyword;
        this.header = articleBuilder.header;
    }

    public Long getId() {
        return id;
    }

    public Long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Long publishDate) {
        this.publishDate = publishDate;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Long> getAuthors() {
        return authors;
    }

    public void addAuthors(Long authors) {
        this.authors.add(authors);
    }

    public void removeAuthors(Long authors) {
        this.authors.remove(authors);
    }

    public void addKeyword(String keyword) {
        this.keyword.add(keyword);
    }

    public void removeKeyword(String keyword) {
        this.keyword.remove(keyword);
    }

    public static class ArticleBuilder {
        public String header;
        Long id;
        String desc;
        String text;
        Long publishDate;
        List<Long> authors;
        List<String> keyword;

        public ArticleBuilder(Long id, Long publishDate, List<Long> authors) {
            this.id = id;
            this.publishDate = publishDate;

            this.authors = new ArrayList<Long>();
            this.authors.addAll(authors);
        }

        public List<String> getKeyword() {
            return keyword;
        }

        public ArticleBuilder setKeyword(List<String> keyword) {
            this.keyword = keyword;
            return this;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public ArticleBuilder setDesc(String desc) {
            this.desc = desc;
            return this;
        }


        public ArticleBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public Article build() {
            return new Article(this);
        }
    }


}
