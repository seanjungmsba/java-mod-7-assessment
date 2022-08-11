package com.example.assessment.service;

import com.example.assessment.dto.AuthorDTO;
import com.example.assessment.dto.CreateBookDTO;
import com.example.assessment.dto.GetBookDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.model.Author;
import com.example.assessment.model.Book;
import com.example.assessment.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private ModelMapper mapper;

    public AuthorDTO create(AuthorDTO authorDTO) {
        Author author = mapper.map(authorDTO, Author.class);
        return mapper.map(repository.save(author), AuthorDTO.class);
    }

    public AuthorDTO updateAuthorById(Long id, AuthorDTO newAuthor) {
        Author author = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));
        author.setId(newAuthor.getId());
        author.setName(newAuthor.getName());
        return mapper.map(repository.save(author), AuthorDTO.class);
    }

    public List<AuthorDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(author -> mapper.map(author, AuthorDTO.class))
                .toList();
    }


    public AuthorDTO getById(Long id) {
        return repository
                .findById(id)
                .map(author -> mapper.map(author, AuthorDTO.class))
                .orElseThrow(() -> new NotFoundException("Author not found"));
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("Author not found");
        }
    }
}
