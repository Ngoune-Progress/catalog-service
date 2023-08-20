package com.example.catalogservice;

import com.example.catalogservice.domain.Book;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import jakarta.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTest {
    private static Validator validator;
    @BeforeAll
    static void  setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
       validator= factory.getValidator();
    }

    @Test
    public  void whenAllFieldCorrectThenValidationSucced(){
        var book = new Book("1234567890","Homeland","Peet",23.0);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();

    }
    @Test
    public  void whenIsbnIsDefinedButIncorrect(){
        var book = new Book("a","Peet","Peet",29.0);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isNotEmpty();

    }

}
