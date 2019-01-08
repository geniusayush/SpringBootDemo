package com.sherlockcodes.libraryDemo.repository;

import com.sherlockcodes.libraryDemo.pojo.Author;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AuthorRepositoryImpl implements AutorRepositroy {
    HashMap<Long, Author> authorList = new HashMap<>();
    long count = 0l;

    @Override
    public Author createAuthor(String name) {
        Author a = new Author(++count);
        a.setName(name);
        authorList.put(count, a);
        return a;
    }

    @Override
    public Author findAuthor(Long id) {
        if (!authorList.containsKey(id)) return null;
        return authorList.get(id);
    }

    @Override
    public boolean exists(String name) {
        for (Author a : authorList.values()) {
            if (a.getName().equals(name)) return false;

        }
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return authorList.containsKey(id);
    }

    @Override
    public Author get(Long id) {
        return authorList.get(id);
    }
}
