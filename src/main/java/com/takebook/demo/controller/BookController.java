package com.takebook.demo.controller;

import com.takebook.demo.dto.BookDto;
import com.takebook.demo.dto.mapper.BookMapper;
import com.takebook.demo.exception.NotFoundException;
import com.takebook.demo.model.Book;
import com.takebook.demo.service.BookService;
import com.takebook.demo.validation.OnCreateValidationGroup;
import com.takebook.demo.validation.OnUpdateValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;


    @PostMapping
    public Book create(@RequestBody @Validated(OnCreateValidationGroup.class) BookDto bookDto) {
        return bookService.create(bookMapper.fromDto(bookDto));
    }

    @GetMapping
    public Page<Book> getAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @GetMapping("/{bookId}")
    public Book getById(@PathVariable(name = "bookId") @NotNull long id) {
        return bookService.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with this" + id + " not found"));
    }

    @GetMapping("/search")
    public Book getByName(@RequestParam(name = "name") @NotNull String name) {
        return bookService.findByName(name)
                .orElseThrow(() -> new NotFoundException("Book with this" + name + " not found"));
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable @NotNull long id,
                           @RequestBody @Validated(OnUpdateValidationGroup.class) BookDto bookDto) {
        return bookService.update(getById(id), bookMapper.fromDto(bookDto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") @NotNull long id) {
        bookService.delete(bookService.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with this " + id + " not found")));
    }

}
