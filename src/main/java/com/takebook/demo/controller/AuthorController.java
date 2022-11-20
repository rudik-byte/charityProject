package com.takebook.demo.controller;

import com.takebook.demo.dto.AuthorDto;
import com.takebook.demo.dto.mapper.AuthorMapper;
import com.takebook.demo.exception.NotFoundException;
import com.takebook.demo.model.Author;
import com.takebook.demo.service.AuthorService;
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
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    private final AuthorMapper authorMapper;


    @PostMapping
    public Author addAuthor(@RequestBody @Validated(OnCreateValidationGroup.class) AuthorDto authorDto) {
        return authorService.create(authorMapper.fromDto(authorDto));
    }

    @GetMapping
    public Page<Author> findAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return authorService.findAll(pageable);
    }

    @GetMapping("/{authorId}")
    public Author findAuthorById(@PathVariable(name = "authorId") @NotNull final long id) {
        return authorService.findById(id)
                .orElseThrow(() -> new NotFoundException("Author with this " + id + " not found"));
    }

    @GetMapping("/search")
    public Author findAuthorByName(@RequestParam(name = "name") @NotNull String name) {
        return authorService.getByName(name)
                .orElseThrow(() -> new NotFoundException("Author with this" + name + " not found"));
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable @NotNull long id,
                               @RequestBody @Validated(OnUpdateValidationGroup.class) AuthorDto authorDto) {
        return authorService.update(findAuthorById(id), authorMapper.fromDto(authorDto));
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable(name = "id") @NotNull long id) {
        authorService.delete(authorService.findById(id)
                .orElseThrow(() -> new NotFoundException("Author with this " + id + " not found")));
    }

}
