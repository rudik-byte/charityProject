package com.takebook.demo.service;

import com.takebook.demo.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AuthorService {

    Author create(Author author);

    Optional<Author> findById(long id);

    Optional<Author> getByName(String name);

    Author update(Author existed, Author newAuthor);

    void delete(Author author);

    Page<Author> findAll(Pageable pageable);
}
