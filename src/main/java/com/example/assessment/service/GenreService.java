package com.example.assessment.service;

import com.example.assessment.dto.genre.GetGenreDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.model.Genre;
import com.example.assessment.repository.BookRepository;
import com.example.assessment.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    // strategy
    // 1. identify genre by its id from genre repository
    // 2. for each books in book repository, if its genreSet contains genre
    //    found from genre repository, add it to the new list of books
    // 3. convert List<Book> to List<GetBookDTO>
    public List<GetGenreDTO> getById(Long id) {
        // identify genre by its id from genre repository
        Genre genreFromGenre = genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre Not Found"));

        List<GetGenreDTO> bookList = new ArrayList<>(); // instead of GetBookDTO?
        // for each books in book repository, if its genreSet contains genre
        // found from genre repository, add it to the new list of books
        bookRepository
                .findAll()
                .stream()
                .forEach(book -> {
                    if (book.getGenreSet().contains(genreFromGenre)) {
                        // convert List<Book> to List<GetBookDTO> using mapper
                        bookList.add(mapper.map(book, GetGenreDTO.class));
                    }
                });
        return bookList;
    }
}
