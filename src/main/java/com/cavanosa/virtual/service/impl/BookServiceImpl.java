package com.cavanosa.virtual.service.impl;


import com.cavanosa.virtual.entity.Book;
import com.cavanosa.virtual.service.BookService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

	@Override
    public List<Book> getAllBooks() {
        return List.of(
            new Book("1", "1984", "George Orwell"),
            new Book("2", "El Aleph", "Jorge Luis Borges")
        );
    }
}