package com.example.charityBook.controller;

import com.example.charityBook.exception.NotFoundException;
import com.example.charityBook.model.Genre;
import com.example.charityBook.service.GenreService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public Genre create(@RequestBody @Validated Genre genre) {
        return genreService.create(genre);
    }

    @GetMapping
    public Page<Genre> getAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return genreService.getAll(pageable);
    }

    @GetMapping("/{genreId}")
    public Genre getById(@PathVariable(name = "genreId") @NotNull long id) {
        return genreService.getById(id);
    }

    @GetMapping("/search")
    public Genre getByName(@RequestParam(name = "name") @NotNull String name) {
        return genreService.getByName(name);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable @NotNull long id,
                             @RequestBody @Validated Genre newGenre) {
        return genreService.update(getById(id), newGenre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable(name = "id") @NotNull long id) {
        genreService.delete(id);
    }
}
