package com.example.assessment.controller;

import com.example.assessment.dto.book.CreateBookDTO;
import com.example.assessment.dto.book.GetBookDTO;
import com.example.assessment.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
    GET /api/books	Gets all the books in the Book table.
    GET /api/books/{id}	Gets a single book with the given ID.
    POST /api/books	Create a book.
    PUT /api/books	Update a book.
    DELETE /api/books/{id}	Delete a book.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public List<GetBookDTO> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public GetBookDTO getBookById(@PathVariable(value = "id") Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public GetBookDTO createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        return bookService.create(createBookDTO);
    }

    @PutMapping("/{id}")
    public GetBookDTO updateBook(@Valid @RequestBody @PathVariable(value = "id") Long id,
                                 @Valid @RequestBody CreateBookDTO newBook) {
        return bookService.updateBookById(id, newBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable(value = "id") Long id) {
        bookService.deleteById(id);
    }

}
