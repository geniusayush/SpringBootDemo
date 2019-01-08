package com.sherlockcodes.libraryDemo.service;

import com.sherlockcodes.libraryDemo.common.LibraryException;
import com.sherlockcodes.libraryDemo.pojo.Article;
import com.sherlockcodes.libraryDemo.pojo.ArticleCreationInput;
import com.sherlockcodes.libraryDemo.pojo.ArticleUpdationInput;

public interface ArticleService {
    Article createArticle(ArticleCreationInput input) throws LibraryException;

    void deleteArticle(Long id) throws LibraryException;

    Article updateArticle(Long id, ArticleUpdationInput input) throws LibraryException;

    Article[] getBytimeStamp(Long start, Long end) throws LibraryException;

    Article getArticle(Long id) throws LibraryException;

    Article[] getByKeyword(String keyword) throws LibraryException;

    Article[] getByAuthor(Long author) throws LibraryException;

    Article[] getAll();
}
