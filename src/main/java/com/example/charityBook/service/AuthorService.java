package com.example.charityBook.service;

import com.example.charityBook.dto.AuthorDto;
import com.example.charityBook.dto.mapper.AuthorMapper;
import com.example.charityBook.exception.NotFoundException;
import com.example.charityBook.model.Author;
import com.example.charityBook.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    public Author create(AuthorDto authorDto) {
        if (authorDto != null) {
            return authorRepository.save(authorMapper.fromDto(authorDto));
        }
        throw new NotFoundException("Author cannot be a 'null'");
    }

    @Transactional
    public Author findById(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with this Id %s not found", id)));
    }

    @Transactional
    public Author getByName(String name) {
        return authorRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Author with this name %s not found", name)));
    }

    @Transactional
    public Author update(Author existed, AuthorDto newAuthor) {
        authorRepository.findById(existed.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Author with this Id %s not found", existed.getId())));
        authorMapper.update(existed, newAuthor);
        return authorRepository.save(authorMapper.fromDto(newAuthor));
    }

    @Transactional
    public void delete(Author author) {
        authorRepository.findById(author.getId())
                .orElseThrow(() -> new NotFoundException(String.format("User with this Id %s not found", author.getId())));
        authorRepository.delete(author);
    }

    @Transactional
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
}
