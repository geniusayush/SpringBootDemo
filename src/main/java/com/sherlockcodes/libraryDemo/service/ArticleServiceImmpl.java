package com.sherlockcodes.libraryDemo.service;

import com.sherlockcodes.libraryDemo.common.LibraryException;
import com.sherlockcodes.libraryDemo.pojo.Article;
import com.sherlockcodes.libraryDemo.pojo.ArticleCreationInput;
import com.sherlockcodes.libraryDemo.pojo.ArticleUpdationInput;
import com.sherlockcodes.libraryDemo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.sherlockcodes.libraryDemo.common.Utils.safe;

@Service
public class ArticleServiceImmpl implements ArticleService {

    @Autowired
    ArticleRepository repository;

    @Autowired
    AuthorService authorService;

    @Override
    public Article createArticle(ArticleCreationInput input) throws LibraryException {
        validateInput(input);
        Article article = repository.create(input);
        for (Long l : article.getAuthors()) {
            authorService.linkArticle(l, article.getId());
        }
        return article;
    }


    @Override
    public void deleteArticle(Long id) throws LibraryException {
        if (id == null) throw new LibraryException("the ID is null");
        if (!repository.contains(id)) {
            throw new LibraryException("The article is not present in the system");
        }
        repository.delete(id);

    }

    @Override
    public Article updateArticle(Long id, ArticleUpdationInput input) throws LibraryException {

        if (id==null || !repository.contains(id)) {
            throw new LibraryException("The article is not present in the system");
        }

        Article older = repository.get(id);
        validateInput(older, input);
        Article newAtricle = repository.update(older, input);
        for (Long k : safe(input.getAuthorToAdd()))
            authorService.linkArticle(k, newAtricle.getId());
        for (Long k : safe(input.getAuthorToRemove()))
            authorService.deLinkArticle(k, newAtricle.getId());
        return newAtricle;
    }


    @Override
    public Article[] getBytimeStamp(Long start, Long end) throws LibraryException {
        if (end == null) end = Long.MAX_VALUE;
        if (start == null) start = 0l;
        if (start > end) throw new LibraryException("start time is greater than end time");

        return repository.getByTime(start, end);
    }

    @Override
    public Article getArticle(Long id) throws LibraryException {
        if (!repository.contains(id)) {
            throw new LibraryException("The article is not present in the system");
        } else return repository.get(id);
    }

    @Override
    public Article[] getByKeyword(String keyword) throws LibraryException {
        return repository.getByKeyword(keyword);
    }

    @Override
    public Article[] getByAuthor(Long author) throws LibraryException {
        Long[] articles = authorService.getArticles(author);
        Article[] list = new Article[articles.length];
        for (int i = 0; i < articles.length; i++) {
            list[i] = repository.get(articles[i]);
        }
        return list;
    }

    @Override
    public Article[] getAll() {
        return repository.getAll().toArray(new Article[0]);
    }

    private void validateInput(ArticleCreationInput input) throws LibraryException {
        if(input==null) throw new LibraryException("input cannot be null");
        if (input.getAuthor() == null || input.getAuthor().size() <= 0)
            throw new LibraryException("the authors cannot be empty");
        if (input.getPublishDate() == null)
            throw new LibraryException("publish date cannot be null");
        for (Long authorid : input.getAuthor())
            if (!authorService.contains(authorid)) throw new LibraryException("The Author dosent exist");
    }

    private void validateInput(Article older, ArticleUpdationInput input) throws LibraryException {
        // if (input.publishDate == 0) throw new LibraryException("publish date cannot be enpty");
        if (older.getAuthors().size() - safe(input.getAuthorToRemove()).length + safe(input.getAuthorToAdd()).length <= 0)
            throw new LibraryException("the authors cannot be empty");
        for (Long authorid : safe(input.getAuthorToAdd()))
            if (!authorService.contains(authorid)) throw new LibraryException("The Author dosent exist");
        for (Long authorid : safe(input.getAuthorToRemove()))
            if (!older.getAuthors().contains(authorid))
                throw new LibraryException("The Author to be removed dosent exist");
    }
}
