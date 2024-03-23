package com.example.charityBook.repository;

import com.example.charityBook.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a from Author a where a.name= :name")
    Optional<Author> findByName(String name);

    Page<Author> findAll(Pageable pageable);
}
