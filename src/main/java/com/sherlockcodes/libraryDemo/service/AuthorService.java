package com.sherlockcodes.libraryDemo.service;

import com.sherlockcodes.libraryDemo.common.LibraryException;
import com.sherlockcodes.libraryDemo.pojo.Author;

public interface AuthorService {
     Author createAuthor(String name) throws LibraryException;

     void linkArticle(Long id, Long article) throws LibraryException;

    void deLinkArticle(Long id, Long article) throws LibraryException;

    Long[] getArticles(Long author) throws LibraryException;

    boolean contains(Long authorid);

    Author getAuthor(Long id);
}
