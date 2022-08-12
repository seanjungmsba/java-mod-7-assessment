package com.example.assessment.service;

import com.example.assessment.dto.CreateUserDTO;
import com.example.assessment.dto.GetBookDTO;
import com.example.assessment.dto.GetUserDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.model.Book;
import com.example.assessment.model.ReadingList;
import com.example.assessment.model.User;
import com.example.assessment.repository.ReadingListRepository;
import com.example.assessment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReadingListRepository readingListRepository;
    @Autowired
    private ModelMapper mapper;

    public GetUserDTO create(CreateUserDTO createUserDTO) {

        // get username from createUserDTO
        String createUserDTOUsername = createUserDTO.getUsername();

        // if the user already exists by its username, throw an error
        if (userRepository.findUserByUsername(createUserDTOUsername) != null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User Already Exists");
        }

        // create User object from createUserDTO
        User user = mapper.map(createUserDTO, User.class);

        // set username and password
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(createUserDTO.getPassword());

        // try to save user
        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "validation errors", e);
        }

        // convert user to GetUserDTO object
        GetUserDTO getUserDTO = mapper.map(user, GetUserDTO.class);

        // set its username
        getUserDTO.setUsername(user.getUsername());

        // return getUserDTO object
        return getUserDTO;
    }

    public void deleteById(Long id) {
        // if user is not found by its id from repository, throw an error
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not Found"));
        // remove the user from reading list
//        Long userToDelete = readingListRepository.findUserByUser(user).getId();
//        readingListRepository.deleteById(userToDelete);
        // finally delete a user from the user repository
        userRepository.deleteById(id);
    }

    // Not a required method but created to validate
    public List<GetUserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    GetUserDTO getUserDTO = mapper.map(user, GetUserDTO.class);
                    getUserDTO.setUsername(user.getUsername());
                    return getUserDTO;
                }).toList();
    }

    // Not a required method but created to validate
    public GetUserDTO getById(Long id) {
        // find a user by its id
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not Found"));
        // convert user to GetUserDTO
        GetUserDTO getUserDTO = mapper.map(user, GetUserDTO.class);
        // get username
        String username = user.getUsername();
        // set author name
        getUserDTO.setUsername(username);
        // return getUserDTO
        return getUserDTO;
    }
}
