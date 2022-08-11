package com.example.assessment.controller;

import com.example.assessment.dto.CreateBookDTO;
import com.example.assessment.dto.GenreDTO;
import com.example.assessment.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
GET /api/genre/{id}/books	Get all books in the genre with the given ID.
 */
@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}/books")
    public List<CreateBookDTO> GetBookById(@PathVariable(value="id") Long id) {
        return genreService.getById(id);
    }

    @PostMapping
    public GenreDTO createGenre(@Valid @RequestBody GenreDTO genreDTO) {
        return genreService.create(genreDTO);
    }

}
