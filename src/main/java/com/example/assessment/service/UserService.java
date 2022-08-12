package com.example.assessment.service;

import com.example.assessment.dto.book.BookInfoDTO;
import com.example.assessment.dto.readinglist.CreateReadingListDTO;
import com.example.assessment.dto.readinglist.GetReadingListDTO;
import com.example.assessment.dto.user.CreateUserDTO;
import com.example.assessment.dto.user.GetUserDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.exception.ValidationException;
import com.example.assessment.model.Author;
import com.example.assessment.model.Book;
import com.example.assessment.model.ReadingList;
import com.example.assessment.model.User;
import com.example.assessment.repository.AuthorRepository;
import com.example.assessment.repository.BookRepository;
import com.example.assessment.repository.ReadingListRepository;
import com.example.assessment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReadingListRepository readingListRepository;
    @Autowired
    private ModelMapper mapper;

    public GetUserDTO create(CreateUserDTO createUserDTO) {

        // get username from createUserDTO
        String createUserDTOUsername = createUserDTO.getUsername();

        // if the user already exists by its username, throw an error
        if (userRepository.findByUsername(createUserDTOUsername) != null) {
            throw new ValidationException();
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
            throw new ValidationException();
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not Found"));
        // remove the user from reading list
        List<ReadingList> readingList = readingListRepository.findByUser(user);
        for (ReadingList list : readingList) {
            // for each book set in reading list existed in repository
            list.getBookSet().forEach(book -> {
                // remove its reading list object from the book set
                book.getReadingSet().remove(list);
            });
        }
        // delete a user from the user repository
        userRepository.deleteById(id);
    }

    // Not a required method but created to validate
    public List<GetUserDTO> getAll() {
        // convert List<User> to List<GetUserDTO>
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not Found"));
        // convert user to GetUserDTO
        GetUserDTO getUserDTO = mapper.map(user, GetUserDTO.class);
        // get username
        String username = user.getUsername();
        // set author name
        getUserDTO.setUsername(username);
        // return getUserDTO
        return getUserDTO;
    }

    // 	Gets the given user’s reading lists
    public List<GetReadingListDTO> getReadingLists(Long id) {

        // try to find user by its id from user repository
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User Not Found"));

        // convert List<ReadingList> to List<GetReadingListDTO>
        return readingListRepository.findByUser(user).stream().map(
                list -> mapper.map(list, GetReadingListDTO.class)).toList();
    }

    // Create a new reading list for the user with the given ID
    public List<BookInfoDTO> createReadingList(Long id,
                                               CreateReadingListDTO createReadingListDTO) {

        // reading list can only be created after user is created
        // thus, if user does not exist, throw an exception
        String userName = createReadingListDTO.getUsername();
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new NotFoundException("User not Found");
        }

        // check if reading list already exists by reading list name and user
        String readingListName = createReadingListDTO.getReadingListName();
        ReadingList readingList = readingListRepository.findByNameAndUser(readingListName, user);

        // if it never existed, create new one
        if (readingList == null) {
            readingList = new ReadingList();
            String listName = createReadingListDTO.getReadingListName();
            readingList.setName(listName);
        }

        // create newBookList, which is a collection consist of new books
        List<BookInfoDTO> newBookList = new ArrayList<BookInfoDTO>();
        // create a copy of readingList to avoid having its data manipulated
        ReadingList readingListFinal = readingList;

        createReadingListDTO
                // create List<BookInfoDTO>
                .getReadingList()
                // for each BookInfoDTO in this list
                .forEach(bookInfoDTO -> {
                    // try to search a book by title and author
                    String bookTitle = bookInfoDTO.getTitle();
                    String bookAuthorName = bookInfoDTO.getAuthor();
                    Author bookAuthor = authorRepository.findByName(bookAuthorName);
                    Book bookSearched = bookRepository.findByTitleAndAuthor(bookTitle, bookAuthor);
                    // if book is found, add it to readingListFinal
                    if (bookSearched != null) {
                        readingListFinal.getBookSet().add(bookSearched);
                    } else {
                        // otherwise, add it to list of new books
                        newBookList.add(bookInfoDTO);
                    }
                });

        // update reading list with new set of books and its user
        readingList.setBookSet(readingListFinal.getBookSet());
        readingList.setUser(user);

        // save
        readingListRepository.save(readingList);
        return newBookList;
    }

    // Gets the given user’s reading list with the id and list_id (NOT WORKING)
    public List<BookInfoDTO> getReadingList(Long id, Long list_id) {

        /*
                // try to find user based on id
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        // try to find reading list based on list_id
        ReadingList readingList = readingListRepository.findById(list_id)
                .orElseThrow(() -> new NotFoundException("Reading List Not Found"));

        // if reading list does not belong to the user, throw an error
        if (readingList.getUser().getId() != user.getId()) {
            throw new ValidationException();
        }

        // Convert Set<Book> to List<BookInfoDTO>
        return readingList.getBookSet().stream()
                .map(bookSet -> mapper.map(bookSet, BookInfoDTO.class)).toList();
         */
        return null;
    }
}
