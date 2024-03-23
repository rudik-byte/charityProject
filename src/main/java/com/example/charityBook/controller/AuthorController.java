package com.example.charityBook.controller;

import com.example.charityBook.dto.AuthorDto;
import com.example.charityBook.model.Author;
import com.example.charityBook.service.AuthorService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public Author addAuthor(@RequestBody @Validated AuthorDto authorDto) {
        return authorService.create(authorDto);
    }

    @GetMapping
    public Page<Author> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return authorService.findAll(pageable);
    }

    @GetMapping("/{authorId}")
    public Author findAuthorById(@PathVariable(name = "authorId") @NotNull long id) {
        return authorService.findById(id);
    }

    @GetMapping("/search")
    public Author findAuthorByName(@RequestParam(name = "name") @NotNull String name) {
        return authorService.getByName(name);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable @NotNull long id,
                               @RequestBody @Validated AuthorDto authorDto) {
        return authorService.update(findAuthorById(id), authorDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable(name = "id") @NotNull long id) {
        authorService.delete(authorService.findById(id));
    }

}
