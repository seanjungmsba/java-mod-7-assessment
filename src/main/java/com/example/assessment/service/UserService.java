package com.example.assessment.service;

import com.example.assessment.dto.CreateUserDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.model.User;
import com.example.assessment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;

    public CreateUserDTO create(CreateUserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        return mapper.map(repository.save(user), CreateUserDTO.class);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("Activity not found");
        }
    }

    public List<CreateUserDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(user -> mapper.map(user, CreateUserDTO.class))
                .toList();
    }

    public CreateUserDTO getById(Long id) {
        return repository
                .findById(id)
                .map(user -> mapper.map(user, CreateUserDTO.class))
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

}
