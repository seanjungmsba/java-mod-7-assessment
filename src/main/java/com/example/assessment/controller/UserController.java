package com.example.assessment.controller;

import com.example.assessment.dto.book.BookInfoDTO;
import com.example.assessment.dto.readinglist.CreateReadingListDTO;
import com.example.assessment.dto.readinglist.GetReadingListDTO;
import com.example.assessment.dto.user.CreateUserDTO;
import com.example.assessment.dto.user.GetUserDTO;
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
    @GetMapping("/{id}/reading_lists")
    public List<GetReadingListDTO> getReadingList(@PathVariable Long id){
        return userService.getReadingLists(id);
    }

    @PostMapping("/{id}/reading_lists")
    public List<BookInfoDTO> createReadingList(@PathVariable(value = "id") Long id,
                                               @RequestBody CreateReadingListDTO readingListDTO){
        return userService.createReadingList(id, readingListDTO);
    }

    @GetMapping("/{id}/reading_lists/{list_id}")
    public List<BookInfoDTO> getReadingListBooks(@PathVariable(value = "id") Long id,
                                                 @PathVariable(value = "list_id") Long list_id) {
        return userService.getReadingList(id,list_id);
    }

}
