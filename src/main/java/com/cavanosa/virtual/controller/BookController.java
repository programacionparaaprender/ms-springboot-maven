package com.cavanosa.virtual.controller;

import com.cavanosa.virtual.entity.Book;
import com.cavanosa.virtual.service.BookService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> books() {
        return bookService.getAllBooks();
    }
}
