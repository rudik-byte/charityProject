package com.example.charityBook.service;

import com.example.charityBook.dto.mapper.BookMapper;
import com.example.charityBook.exception.NotFoundException;
import com.example.charityBook.model.Book;
import com.example.charityBook.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    public Book create(Book book) {
        if (book != null) {
            return bookRepository.save(book);
        }
        throw new NotFoundException("Book cannot be a 'null'");
    }

    @Transactional
    public Book update(Book existed, Book newBook) {
        bookRepository.findById(existed.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Book with this Id %s not found", existed.getId())));
        bookMapper.update(existed, bookMapper.toDto(newBook));
        return bookRepository.save(newBook);
    }

    @Transactional
    public Book findById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with this Id %s not found", id)));
    }

    @Transactional
    public Book findByName(String name) {
        return bookRepository.findBookByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Book with this name %s not found", name)));
    }

    @Transactional
    public void delete(long id) {
        var existedBook = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with this Id %s not found", id)));
        bookRepository.delete(existedBook);
    }

    @Transactional
    public Page<Book> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

}
