package com.example.assessment.service;

import com.example.assessment.dto.CreateBookDTO;
import com.example.assessment.dto.GenreDTO;
import com.example.assessment.dto.GetBookDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.model.Book;
import com.example.assessment.repository.BookRepository;
import com.example.assessment.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper mapper;

    public List<GetBookDTO> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(book -> mapper.map(book, GetBookDTO.class))
                .toList();
    }

    public GetBookDTO getById(Long id) {
        return bookRepository
                .findById(id)
                .map(book -> mapper.map(book, GetBookDTO.class))
                .orElseThrow(() -> new NotFoundException("Book not found"));
    }

    public CreateBookDTO create(CreateBookDTO createBookDTO) {
        try {
            Book book = mapper.map(createBookDTO, Book.class);
            return mapper.map(bookRepository.save(book), CreateBookDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("something went wrong when creating a book");
        }
        return null;
    }

    public CreateBookDTO updateBookById(Long id, CreateBookDTO bookData) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        book.setId(bookData.getId());
        book.setPublished(bookData.getPublished());
        book.setTitle(bookData.getTitle());
        book.setPages(bookData.getPages());
        return mapper.map(bookRepository.save(book), CreateBookDTO.class);
    }

    public void deleteById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new NotFoundException("Book not found");
        }
    }
}
