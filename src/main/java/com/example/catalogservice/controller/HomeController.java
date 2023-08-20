package com.example.catalogservice.controller;

import com.example.catalogservice.domain.Book;
import com.example.catalogservice.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class HomeController {
    private final BookService bookService;

    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public  Iterable<Book> findAll(){
        return  bookService.viewListOfBook();
    }
    @GetMapping("{isbn}")
    public Book getByIsbn(@PathVariable String isbn){
        return  bookService.viewBookDetail(isbn);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book){
        return  bookService.addBookToCatalog(book);
    }
    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String isbn){
         bookService.removeBookFromCatalog(isbn);
    }
    @PutMapping("{isbn}")
    public Book updateBook(@PathVariable String isbn,@Valid @RequestBody Book book){
        return  bookService.editBookDetail(isbn,book);
    }
    @GetMapping("/")
    public String getGreeting(){
        return "Hi your are welcome";
    }
}
