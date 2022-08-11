package com.example.assessment.controller;

import com.example.assessment.dto.AuthorDTO;
import com.example.assessment.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public AuthorDTO createAuthor(@Valid @RequestBody AuthorDTO author) {
        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public AuthorDTO updateAuthor(@Valid @RequestBody @PathVariable(value = "id") Long id,
                                  @Valid @RequestBody AuthorDTO newAuthor) {
        return authorService.updateAuthorById(id, newAuthor);
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO GetAuthorById(@PathVariable(value = "id") Long id) {
        return authorService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorById(@PathVariable(value = "id") Long id) {
        authorService.deleteById(id);
    }

}
