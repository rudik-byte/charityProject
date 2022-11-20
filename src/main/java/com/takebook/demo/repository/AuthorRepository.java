package com.takebook.demo.repository;

import com.takebook.demo.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a from Author a where a.name= :name")
    Optional<Author> findByName( String name);

    Page<Author> findAll (Pageable pageable);
}
