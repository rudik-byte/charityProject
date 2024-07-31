package com.example.charityBook.service;

import com.example.charityBook.exception.NotFoundException;
import com.example.charityBook.model.Genre;
import com.example.charityBook.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Genre create(Genre genre) {
        if (genre != null) {
            return genreRepository.save(genre);
        }
        throw new NotFoundException("Genre cannot be a 'null'");
    }

    public Genre update(Genre existed, Genre newGenre) {
        genreRepository.findById(existed.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Genre with this Id %s not found", existed.getId())));
        existed.setName(newGenre.getName());
        return genreRepository.save(existed);
    }

    public Genre getById(long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre with this Id %s not found", id)));
    }

    public Genre getByName(String name) {
        return genreRepository.findGenreByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Genre with this name %s not found", name)));
    }

    public void delete(long id) {
        var existedGenre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre with this Id %s not found", id)));
        genreRepository.delete(existedGenre);
    }

    public Page<Genre> getAll(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
