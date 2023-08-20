package com.example.catalogservice.service;

import com.example.catalogservice.domain.Book;
import com.example.catalogservice.exception.BookAlreadyExistsException;
import com.example.catalogservice.exception.BookNotFoundException;
import com.example.catalogservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public  Iterable<Book> viewListOfBook(){
        return  bookRepository.findAll();
    }
    public Book viewBookDetail(String isbn){
        return  bookRepository.findByIsbn(isbn).orElseThrow(()->new  BookNotFoundException(isbn));
    }
    public Book addBookToCatalog(Book book){
        if(bookRepository.existByIsbn(book.getIsbn())){
            throw new BookAlreadyExistsException(book.getIsbn());
        }
        return  bookRepository.save(book);
    }
    public void removeBookFromCatalog(String isbn){
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetail(String isbn,Book book){
       return bookRepository.findByIsbn(book.getIsbn()).map(existingBook->{
           var bookToUpdate = new Book();
           bookToUpdate.setIsbn(isbn);
           bookToUpdate.setAuthor(book.getAuthor());
           bookToUpdate.setPrice(book.getPrice());
           bookToUpdate.setTitle(book.getTitle());

           return bookRepository.save(bookToUpdate);
       }).orElseGet(()->addBookToCatalog(book));
    }
}
