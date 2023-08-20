package com.example.catalogservice.repository;

import com.example.catalogservice.domain.Book;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryBookRepositoryIml implements BookRepository{
    private static  final Map<String, Book> books=  new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {

        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String Isbn) {
        return existByIsbn(Isbn) ? Optional.of(books.get(Isbn)): Optional.empty();
    }

    @Override
    public Boolean existByIsbn(String Isbn) {
        return books.get(Isbn) !=null;
    }

    @Override
    public Book save(Book book) {
        books.put(book.getIsbn(),book);
        return book;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        books.remove(isbn);
    }
}
