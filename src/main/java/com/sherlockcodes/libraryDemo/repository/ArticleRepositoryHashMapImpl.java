package com.sherlockcodes.libraryDemo.repository;

import com.sherlockcodes.libraryDemo.common.LibraryException;
import com.sherlockcodes.libraryDemo.pojo.Article;
import com.sherlockcodes.libraryDemo.pojo.ArticleCreationInput;
import com.sherlockcodes.libraryDemo.pojo.ArticleUpdationInput;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.sherlockcodes.libraryDemo.common.Utils.safe;

@Repository
public class ArticleRepositoryHashMapImpl implements ArticleRepository {

    Long count = 0l;

    Map<Long, Article> map = new HashMap<>();

    @Override
    public boolean contains(Long id) {

        return map.containsKey(id);
    }

    @Override
    public void delete(Long id) {
        map.remove(id);
    }

    @Override
    public Article[] getByTime(Long start, Long end) {
        ArrayList<Article> list = new ArrayList<>();
        for (Article a : map.values()) {
            if (a.getPublishDate() >= start && a.getPublishDate() <= end) list.add(a);
        }
        return list.toArray(new Article[0]);
    }

    @Override
    public Article get(Long id) {
        return map.get(id);
    }

    @Override
    public ArrayList<Article> getAll() {
        ArrayList<Article> list = new ArrayList<>();
        list.addAll(map.values());
        return list;
    }

    @Override
    public Article[] getByKeyword(String keyword) throws LibraryException {
        if (keyword == null) throw new LibraryException("keywords cannot be null");
        ArrayList<Article> list = new ArrayList<>();
        for (Article a : map.values()) {
            if (a.getKeyword().contains(keyword)) list.add(a);
        }
        return list.toArray(new Article[0]);
    }

    @Override
    public Article update(Article older, ArticleUpdationInput input) throws LibraryException {
        if (older == null || input == null) throw new LibraryException("entities cannot be null");
        if (input.getPublishDate() != null) older.setPublishDate(input.getPublishDate());
        if (input.getDesc() != null) older.setDesc(input.getDesc());
        if (input.getHeader() != null) older.setHeader(input.getHeader());
        for (String k : safe(input.getKeywordtoAdd()))
            if (!older.getKeyword().contains(k)) older.addKeyword(k);
        for (String k : safe(input.getKeywordtoRemove()))
            older.removeKeyword(k);
        for (Long k : safe(input.getAuthorToAdd()))
            older.addAuthors(k);
        for (Long k : safe(input.getAuthorToRemove()))
            older.removeAuthors(k);
        map.put(older.getId(), older);
        return older;
    }

    @Override
    public Article create(ArticleCreationInput input) throws LibraryException {
        if (input.getAuthor() == null) throw new LibraryException("author not present");
        Article.ArticleBuilder builder = new Article.ArticleBuilder(++count, input.getPublishDate(), input.getAuthor());
        builder.setDesc((input.getDesc() != null) ? input.getDesc() : "");
        builder.setHeader((input.getHeader() != null) ? input.getHeader() : "");
        builder.setText((input.getText() != null) ? input.getText() : "");
        builder.setKeyword(new ArrayList<>());
        if (input.getKeyword() != null)
            builder.getKeyword().addAll(safe(input.getKeyword()));
        Article a = builder.build();
        map.put(a.getId(), a);
        return a;
    }
}
