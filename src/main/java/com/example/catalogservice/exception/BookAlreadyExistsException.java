package com.example.catalogservice.exception;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String message) {
        super("A book with id "+message+" already exist");
    }
}
