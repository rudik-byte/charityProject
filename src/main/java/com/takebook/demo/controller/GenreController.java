package com.takebook.demo.controller;

import com.takebook.demo.exception.NotFoundException;
import com.takebook.demo.model.Genre;
import com.takebook.demo.service.GenreService;
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
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public Genre create(@RequestBody @Validated(OnCreateValidationGroup.class) Genre genre) {
        return genreService.create(genre);
    }

    @GetMapping
    public Page<Genre> getAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return genreService.getAll(pageable);
    }

    @GetMapping("/{genreId}")
    public Genre getById(@PathVariable(name = "genreId") @NotNull long id) {
        return genreService.getById(id)
                .orElseThrow(() -> new NotFoundException("Genre with this" + id + " not found"));
    }

    @GetMapping("/search")
    public Genre getByName(@RequestParam(name = "name") @NotNull String name) {
        return genreService.getByName(name)
                .orElseThrow(() -> new NotFoundException("Genre with this" + name + " not found"));
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable @NotNull long id,
                             @RequestBody @Validated(OnUpdateValidationGroup.class) Genre newGenre) {
        return genreService.update(getById(id), newGenre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable(name = "id") @NotNull long id) {
        genreService.delete(genreService.getById(id)
                .orElseThrow(() -> new NotFoundException("Genre with this " + id + " not found")));
    }
}
