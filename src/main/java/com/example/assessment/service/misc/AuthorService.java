package com.example.assessment.service.misc;

import com.example.assessment.dto.author.GetAuthorDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.model.Author;
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

    public GetAuthorDTO create(GetAuthorDTO getAuthorDTO) {
        Author author = mapper.map(getAuthorDTO, Author.class);
        return mapper.map(repository.save(author), GetAuthorDTO.class);
    }

    public GetAuthorDTO updateAuthorById(Long id, GetAuthorDTO newAuthor) {
        Author author = repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));
        author.setId(newAuthor.getId());
        author.setName(newAuthor.getName());
        return mapper.map(repository.save(author), GetAuthorDTO.class);
    }

    public List<GetAuthorDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(author -> mapper.map(author, GetAuthorDTO.class))
                .toList();
    }


    public GetAuthorDTO getById(Long id) {
        return repository
                .findById(id)
                .map(author -> mapper.map(author, GetAuthorDTO.class))
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
