package com.takebook.demo.service;

import com.takebook.demo.model.Book;
import com.takebook.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {

    Book create(Book book);

    Book update(Book existed, Book newBook);

    Optional<Book> findById(long id);

    Optional<Book> findByName(String name);

    void delete(Book book);

    Page<Book> getAll(Pageable pageable);

    boolean isBookLimitReached(User user);
}
