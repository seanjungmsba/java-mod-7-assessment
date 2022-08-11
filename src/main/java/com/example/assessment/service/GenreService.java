package com.example.assessment.service;

import com.example.assessment.dto.CreateBookDTO;
import com.example.assessment.dto.GenreDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.model.Book;
import com.example.assessment.model.Genre;
import com.example.assessment.repository.BookRepository;
import com.example.assessment.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    public List<CreateBookDTO> getById(Long id) {
        // create new BookDTO list
        List<CreateBookDTO> genreBookList = new ArrayList<>();
        // get Optional<Book> from Book Repository
        Optional<Book> bookOptional = bookRepository.findById(id);
        // if book is present, add it to newList;
        if (bookOptional.isPresent()) {
            Book existingBook = bookOptional.get();
            genreBookList.add(mapper.map(existingBook, CreateBookDTO.class));
        // otherwise, throw NotFoundException
        } else {
            throw new NotFoundException("book is not found!");
        }
        // return newList
        return genreBookList;
    }

    public GenreDTO create(GenreDTO genreDTO) {
        try {
            Genre genre = mapper.map(genreDTO, Genre.class);
            return mapper.map(genreRepository.save(genre), GenreDTO.class);
        } catch (Exception e) {
            log.error("ERROR");
            throw new NotFoundException("Book not found to match with genre");
        }
    }

}
