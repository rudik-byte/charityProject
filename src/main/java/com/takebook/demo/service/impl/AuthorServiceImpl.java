package com.takebook.demo.service.impl;

import com.takebook.demo.dto.mapper.AuthorMapper;
import com.takebook.demo.exception.NotFoundException;
import com.takebook.demo.model.Author;
import com.takebook.demo.repository.AuthorRepository;
import com.takebook.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Override
    public Author create(Author author) {
        if (author != null) {
            return authorRepository.save(author);
        }
        throw new NotFoundException("Author cannot be a 'null'");
    }

    @Override
    @Transactional
    public Optional<Author> findById(long id) {
        authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with this Id %s not found", id)));
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Author> getByName(String name) {
        authorRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Author with this name %s not found", name)));
        return authorRepository.findByName(name);
    }

    @Override
    @Transactional
    public Author update(Author existed, Author newAuthor) {
        authorRepository.findById(existed.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Author with this Id %s not found", existed.getId())));
        authorMapper.update(existed, authorMapper.toDto(newAuthor));
        return authorRepository.save(newAuthor);
    }

    @Override
    @Transactional
    public void delete(Author author) {
        authorRepository.findById(author.getId())
                .orElseThrow(() -> new NotFoundException(String.format("User with this Id %s not found", author.getId())));
        authorRepository.delete(author);
    }

    @Override
    @Transactional
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
}
