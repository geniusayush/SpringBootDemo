package com.sherlockcodes.libraryDemo.service;

import com.sherlockcodes.libraryDemo.common.LibraryException;
import com.sherlockcodes.libraryDemo.pojo.Author;
import com.sherlockcodes.libraryDemo.repository.AutorRepositroy;
import com.sherlockcodes.libraryDemo.web.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    @Autowired
    AutorRepositroy repositroy;

    @Override
    public Author createAuthor(String name) throws LibraryException {
        if (name.trim().equals("")) throw new LibraryException("The author name cannot be empty");
        if (repositroy.exists(name)) throw new LibraryException("The author already exists");
        return repositroy.createAuthor(name);
    }

    @Override
    public void linkArticle(Long id, Long article) throws LibraryException {
        if (!repositroy.exists(id)) throw new LibraryException("the author dosent exist");
        getAuthor(id).addArticles(article);

    }

    @Override
    public void deLinkArticle(Long id, Long article) throws LibraryException {
        if (!repositroy.exists(id)) throw new LibraryException("the author dosent exist");
        getAuthor(id).removeArticles(article);

    }

    @Override
    public Long[] getArticles(Long author) throws LibraryException {
        if (!repositroy.exists(author)) throw new LibraryException("the author dosent exist");
        return getAuthor(author).getArticles().toArray(new Long[0]);
    }

    @Override
    public boolean contains(Long authorid) {
        return (repositroy.exists(authorid));

    }

    @Override
    public Author getAuthor(Long id) {
        return repositroy.get(id);
    }

}
