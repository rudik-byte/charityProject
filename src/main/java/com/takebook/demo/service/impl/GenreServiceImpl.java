package com.takebook.demo.service.impl;

import com.takebook.demo.exception.NotFoundException;
import com.takebook.demo.model.Genre;
import com.takebook.demo.repository.GenreRepository;
import com.takebook.demo.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Genre create(Genre genre) {
        if (genre != null) {
            return genreRepository.save(genre);
        }
        throw new NotFoundException("Genre cannot be a 'null'");
    }

    @Override
    public Genre update(Genre existed, Genre newGenre) {
        genreRepository.findById(existed.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Genre with this Id %s not found", existed.getId())));
        existed.setName(newGenre.getName());
        return genreRepository.save(existed);
    }

    @Override
    public Optional<Genre> getById(long id) {
        genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre with this Id %s not found", id)));
        return genreRepository.findById(id);
    }

    @Override
    public Optional<Genre> getByName(String name) {
        genreRepository.findGenreByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Genre with this name %s not found", name)));
        return genreRepository.findGenreByName(name);
    }

    @Override
    public void delete(Genre genre) {
        genreRepository.findById(genre.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Genre with this Id %s not found", genre.getId())));
        genreRepository.delete(genre);
    }


    @Override
    public Page<Genre> getAll(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }
}
