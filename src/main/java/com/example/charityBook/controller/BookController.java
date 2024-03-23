package com.example.charityBook.controller;

import com.example.charityBook.dto.BookDto;
import com.example.charityBook.dto.mapper.BookMapper;
import com.example.charityBook.exception.NotFoundException;
import com.example.charityBook.model.Book;
import com.example.charityBook.service.BookService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;


    @PostMapping
    public Book create(@RequestBody @Validated BookDto bookDto) {
        return bookService.create(bookMapper.fromDto(bookDto));
    }

    @GetMapping
    public Page<Book> getAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @GetMapping("/{bookId}")
    public Book getById(@PathVariable(name = "bookId") @NotNull long id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    public Book getByName(@RequestParam(name = "name") @NotNull String name) {
        return bookService.findByName(name);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable @NotNull long id,
                           @RequestBody @Validated BookDto bookDto) {
        return bookService.update(getById(id), bookMapper.fromDto(bookDto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") @NotNull long id) {
        bookService.delete(id);
    }

}
