package com.example.catalogservice;

import com.example.catalogservice.exception.BookNotFoundException;
import com.example.catalogservice.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@WebMvcTest
public class BookControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @Test
    void whenGetBookGetBookDoNotExistThenShouldReturn404() throws Exception {
        String isbn ="1234567890";
        given(bookService.viewBookDetail(isbn))
                .willThrow(BookNotFoundException.class);
        mockMvc.perform(get("/books/"+isbn))
                .andExpect(status().isNotFound());
    }
}
