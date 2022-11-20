package com.takebook.demo.service.impl;

import com.takebook.demo.dto.mapper.BookMapper;
import com.takebook.demo.exception.NotFoundException;
import com.takebook.demo.model.Book;
import com.takebook.demo.model.User;
import com.takebook.demo.repository.BookRepository;
import com.takebook.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    public Book create(Book book) {
        if (book != null) {
            return bookRepository.save(book);
        }
        throw new NotFoundException("Book cannot be a 'null'");
    }

    @Override
    @Transactional
    public Book update(Book existed, Book newBook) {
        bookRepository.findById(existed.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Book with this Id %s not found", existed.getId())));
        bookMapper.update(existed, bookMapper.toDto(newBook));
        return bookRepository.save(newBook);
    }

    @Override
    @Transactional
    public Optional<Book> findById(long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with this Id %s not found", id)));
        return bookRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Book> findByName(String name) {
        bookRepository.findBookByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Book with this name %s not found", name)));
        return bookRepository.findBookByName(name);
    }

    @Override
    @Transactional
    public void delete(Book book) {
        bookRepository.findById(book.getId())
                .orElseThrow(() -> new NotFoundException(String.format("User with this Id %s not found", book.getId())));
        bookRepository.delete(book);
    }

    @Override
    @Transactional
    public Page<Book> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public boolean isBookLimitReached(User user) {
        if (user.getBooksRented() >= user.getBookLimit()) {
            user.setBookLimitReached(true);
            return true;
        } else return false;
    }
}
