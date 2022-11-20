package com.takebook.demo.service;

import com.takebook.demo.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Genre create(Genre genre);

    Genre update(Genre existed, Genre newGenre);

    Optional<Genre> getById(long id);

    Optional<Genre> getByName(String name);

    void delete(Genre genre);

    Page<Genre> getAll(Pageable pageable);
}
