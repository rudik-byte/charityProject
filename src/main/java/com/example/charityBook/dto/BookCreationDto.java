package com.example.charityBook.dto;


import com.example.charityBook.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookCreationDto {

    private List<Book> books;

    public BookCreationDto() {
        this.books = new ArrayList<>();
    }

    public BookCreationDto(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
