package com.example.charityBook.controller;

import com.example.charityBook.dto.BookCreationDto;
import com.example.charityBook.dto.BookDto;
import com.example.charityBook.dto.mapper.BookMapper;
import com.example.charityBook.model.Book;
import com.example.charityBook.service.BookService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookMapper bookMapper;

//    @RequestMapping(value = "/listBooks", method = RequestMethod.GET)
//    public String listBooks(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
//        final int currentPage = page.orElse(1);
//        final int pageSize = size.orElse(5);
//
//        Page<Book> bookPage = bookService.getAll(PageRequest.of(currentPage - 1, pageSize));
//
//        model.addAttribute("bookPage", bookPage);
//
//        int totalPages = bookPage.getTotalPages();
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//
//        return "listBooks.html";
//    }

    @GetMapping(value = "/all")
    public String showAll(Model model) {
        model.addAttribute("books", bookService.findAll());

        return "allBooks";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        BookCreationDto booksForm = new BookCreationDto();

        for (int i = 1; i <= 3; i++) {
            booksForm.addBook(new Book());
        }

        model.addAttribute("form", booksForm);

        return "createBooksForm";
    }

    @GetMapping(value = "/edit")
    public String showEditForm(Model model) {
        List<Book> books = new ArrayList<>();
        bookService.findAll()
                .iterator()
                .forEachRemaining(books::add);

        model.addAttribute("form", new BookCreationDto(books));

        return "editBooksForm";
    }

    @PostMapping(value = "/save")
    public String saveBooks(@ModelAttribute BookCreationDto form, Model model) {
        bookService.saveAll(form.getBooks());

        model.addAttribute("books", bookService.findAll());

        return "redirect:/books/all";
    }

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
