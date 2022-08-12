package com.example.assessment.controller;

import com.example.assessment.dto.CreateReadingListDTO;
import com.example.assessment.dto.CreateUserDTO;
import com.example.assessment.dto.GetReadingListDTO;
import com.example.assessment.dto.GetUserDTO;
import com.example.assessment.service.ReadingListService;
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

    @Autowired
    private ReadingListService readingListService;

    @PostMapping
    public GetUserDTO createUser(@Valid @RequestBody CreateUserDTO userDTO) {
        return userService.create(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable(value = "id") Long id) {
        userService.deleteById(id);
    }

    @GetMapping
    public List<GetUserDTO> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public GetUserDTO GetUserById(@PathVariable(value = "id") Long id) {
        return userService.getById(id);
    }

    /*
    GET /api/users/{id}/reading_lists	Gets the given user’s reading lists.
    POST /api/users/{id}/reading_lists	Create a new reading list for the user with the given ID.
    GET /api/users/{id}/reading_lists/{list_id}	Gets the given user’s reading list with the ID list_id.
     */
//    @PostMapping("/{id}/reading_lists")
//    public CreateReadingListDTO createReadingList(@Valid @RequestBody CreateReadingListDTO createReadingListDTO) {
//        return readingListService.create(createReadingListDTO);
//    }
//
//    @GetMapping("/{id}/reading_lists")
//    public GetUserDTO GetReadingListDTOById(@PathVariable(value = "id") Long id) {
//        return readingListService.getById(id);
//    }
//
//
//    @GetMapping("/{id}/reading_lists/{list_id}")
//    public GetReadingListDTO GetReadingListDTOByListId(@PathVariable(value = "list_id") Long list_id) {
//        return readingListService.getByIdAndListId(list_id);
//    }
}
