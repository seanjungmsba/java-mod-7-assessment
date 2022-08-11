package com.example.assessment.controller;

import com.example.assessment.dto.CreateUserDTO;
import com.example.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
POST api/users => Create a user.
DELETE api/users/{id} => Delete a user.
GET /api/users/{id}/reading_lists => Gets the given user’s reading lists.
POST /api/users/{id}/reading_lists => Create a new reading list for the user with the given ID.
GET /api/users/{id}/reading_lists/{list_id} => Gets the given user’s reading list with the ID list_id.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public CreateUserDTO createUser(@Valid @RequestBody CreateUserDTO userDTO) {
        return userService.create(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable(value = "id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping
    public List<CreateUserDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public CreateUserDTO GetUserById(@PathVariable(value="id") Long id) {
        return userService.getById(id);
    }

//    @GetMapping("/{id}")
//    public GetUserDTO GetUserById(@PathVariable(value="id") Long id) {
//        return userService.getById(id);
//    }
//
//    @PostMapping
//    public CreateUserDTO createUserById(@Valid @RequestBody BookDTO bookDTO) {
//        return bookService.create(bookDTO);
//    }
}
