package com.example.assessment.controller;

import com.example.assessment.dto.GetAuthorDTO;
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
    public GetAuthorDTO createAuthor(@Valid @RequestBody GetAuthorDTO author) {
        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public GetAuthorDTO updateAuthor(@Valid @RequestBody @PathVariable(value = "id") Long id,
                                     @Valid @RequestBody GetAuthorDTO newAuthor) {
        return authorService.updateAuthorById(id, newAuthor);
    }

    @GetMapping
    public List<GetAuthorDTO> getAllAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public GetAuthorDTO GetAuthorById(@PathVariable(value = "id") Long id) {
        return authorService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthorById(@PathVariable(value = "id") Long id) {
        authorService.deleteById(id);
    }

}
