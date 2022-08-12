package com.example.assessment.controller;

import com.example.assessment.dto.GetBookDTO;
import com.example.assessment.dto.GetGenreDTO;
import com.example.assessment.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
GET /api/genre/{id}/books	Get all books in the genre with the given ID.
 */
@RestController
@RequestMapping("/api/genre")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}/books") // instead of GetBookDTO?
    public List<GetGenreDTO> GetBooksById(@PathVariable(value = "id") Long id) {
        return genreService.getById(id);
    }

}
