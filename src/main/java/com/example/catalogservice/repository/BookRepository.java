package com.example.catalogservice.repository;

import com.example.catalogservice.domain.Book;

import java.util.Optional;

public interface BookRepository {
    Iterable<Book> findAll();
    Optional<Book> findByIsbn(String Isbn);
    Boolean existByIsbn(String Isbn);
    Book save(Book book);
    void deleteByIsbn(String isbn);
}
