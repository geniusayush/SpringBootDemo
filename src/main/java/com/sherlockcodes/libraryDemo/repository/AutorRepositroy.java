package com.sherlockcodes.libraryDemo.repository;

import com.sherlockcodes.libraryDemo.pojo.Author;

public interface AutorRepositroy {
    Author createAuthor(String name);

    Author findAuthor(Long name);


    boolean exists(String name);

    boolean exists(Long id);

    Author get(Long id);
}
