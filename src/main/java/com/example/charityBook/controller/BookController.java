package com.example.charityBook.controller;

import com.example.charityBook.dto.BookDto;
import com.example.charityBook.dto.mapper.BookMapper;
import com.example.charityBook.model.Book;
import com.example.charityBook.service.AuthorService;
import com.example.charityBook.service.BookService;
import com.example.charityBook.service.GenreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private BookMapper bookMapper;


    @GetMapping("/new")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new BookDto());
        model.addAttribute("allAuthors", authorService.findAll());
        model.addAttribute("allGenres", genreService.findAll());
        return "createBooksForm";
    }

    @GetMapping(value = "/all")
    public String showAll(Model model) {
        model.addAttribute("books", bookService.findAll());

        return "allBooks";
    }

    @PostMapping("/create")
    public String saveBook(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createBooksForm";
        }

        bookService.create(bookMapper.fromDto(bookDto));
        return "redirect:/all";
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
