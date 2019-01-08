package com.sherlockcodes.libraryDemo.repository;

import com.sherlockcodes.libraryDemo.common.LibraryException;
import com.sherlockcodes.libraryDemo.pojo.Article;
import com.sherlockcodes.libraryDemo.pojo.ArticleCreationInput;
import com.sherlockcodes.libraryDemo.pojo.ArticleUpdationInput;

import java.util.ArrayList;

public interface ArticleRepository {
    boolean contains(Long id);

    void delete(Long id);

    Article[] getByTime(Long start, Long end);

    Article get(Long id);

    ArrayList<Article> getAll();

    Article[] getByKeyword(String keyword) throws LibraryException;

    Article update(Article older, ArticleUpdationInput input) throws LibraryException;

    Article create(ArticleCreationInput input) throws LibraryException;
}
