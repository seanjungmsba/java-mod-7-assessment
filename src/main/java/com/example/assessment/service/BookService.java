package com.example.assessment.service;

import com.example.assessment.dto.book.CreateBookDTO;
import com.example.assessment.dto.book.GetBookDTO;
import com.example.assessment.exception.NotFoundException;
import com.example.assessment.exception.ValidationException;
import com.example.assessment.model.Author;
import com.example.assessment.model.Book;
import com.example.assessment.model.Genre;
import com.example.assessment.repository.AuthorRepository;
import com.example.assessment.repository.BookRepository;
import com.example.assessment.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    private ModelMapper mapper;

    // we have to manually set author and genre fields to properly return fields in GetBookDTO
    public List<GetBookDTO> getAll() {
        return bookRepository.findAll() // get all books from repository
                .stream()
                .map(book -> {
                    // for each book, convert to GetBookDTO
                    GetBookDTO getBookDTO = mapper.map(book, GetBookDTO.class);
                    // get name of author
                    String author = book.getAuthor().getName();
                    // set author
                    getBookDTO.setAuthor(author);
                    // get list of genres
                    List<String> genres = book.getGenreSet().stream().map(g -> g.getName()).toList();
                    // set genre
                    getBookDTO.setGenres(genres);
                    // return BookDTO
                    return getBookDTO;
                })
                .toList(); // convert to List<GetBookDTO>
    }

    public GetBookDTO getById(Long id) {
        // find a book by its id
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not Found"));
        // convert a book to GetBookDTO
        GetBookDTO getBookDTO = mapper.map(book, GetBookDTO.class);
        // get author name
        String author = book.getAuthor().getName();
        // set author name
        getBookDTO.setAuthor(author);
        // get list of genre
        List<String> genre = book.getGenreSet().stream().map(g -> g.getName()).toList();
        // set genre
        getBookDTO.setGenres(genre);
        // return GetBookDTO
        return getBookDTO;
    }

    public GetBookDTO create(CreateBookDTO createBookDTO) {

        // get book title from createBookDTO
        String bookTitle = createBookDTO.getTitle();
        // get book author from createBookDTO
        Author bookAuthor = authorRepository.findByName(createBookDTO.getAuthor());
        // if the book already exists by its title and author, throw an error
        if (bookRepository.findByTitleAndAuthor(bookTitle, bookAuthor) != null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Book Already Exists");
        }

        // create Book object from createBookDTO
        // why? => because we have to manually set author and genre objects in CreateBookDTO and Book
        // many-to-one: unidirectional mapping | many-to-many: bidirectional mapping
        Book book = mapper.map(createBookDTO, Book.class);

        // 1. set the author of the book
        Author author = authorRepository.findByName(createBookDTO.getAuthor());
        // if author is not found from the author repository,
        // create new author and add it to author repository
        if (author == null) {
            author = new Author();
            author.setName(createBookDTO.getAuthor());
            authorRepository.save(author);
        }
        // set its author
        book.setAuthor(author);

        // 2. set a genreSet of the book
        Set<Genre> genreSet = book.getGenreSet();
        // make a copy of book so that we can add it to Genre's bookSet
        // without having to worry about the changes I will make to the book object afterwards
        Book bookCopy = book;

        // For each genre input in CreateBookDTO, try to retrieve Genre object by its name
        createBookDTO.getGenre().forEach(genreName -> {
            Genre genre = genreRepository.findByName(genreName);
            // if the genre is found, update the Genre object's bookSet with the new book
            if (genre != null) {
                genre.getBookSet().add(bookCopy);
                // if the genre doesn't yet exist, create new Genre object and save it to genre repository
            } else {
                genre = new Genre();
                genre.setName(genreName);
                genre.getBookSet().add(bookCopy);
                genreRepository.save(genre);
            }
            // finally add a Genre to the Book object's genreSet
            genreSet.add(genre);
        });


        try {
            // save book
            book = bookRepository.save(book);
        } catch (Exception e) {
            // catch exception if there is any error
            System.err.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "validation errors", e);
        }
        // convert book to GetBookDTO object
        GetBookDTO getBookDTO = mapper.map(book, GetBookDTO.class);
        // set its author field
        getBookDTO.setAuthor(book.getAuthor().getName());
        // set its genre field
        getBookDTO.setGenres(book.getGenreSet().stream().map(genre -> genre.getName()).toList());
        return getBookDTO;
    }

    // we have to update Author and Genre field accordingly when updating a Book info since these are linked together
    public GetBookDTO updateBookById(Long id, CreateBookDTO updateBookDTO) {

        // if the book doesn't exist by its id, throw an error
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not Found"));

        // 1. update author by first attempting to retrieve author from author repository
        Author author = authorRepository.findByName(updateBookDTO.getAuthor());

        // if author doesn't exist, create new author and save it to author repository
        if (author == null) {
            author = new Author();
            author.setName(updateBookDTO.getAuthor());
            authorRepository.save(author);
        }
        // set its author
        book.setAuthor(author);

        // 2. update the genres
        Set<Genre> genreSet = book.getGenreSet();

        // create a copy of its book so that intermediary changes in book won't affect bookCopy
        Book bookCopy = book;

        // for each genre in genreSet in a book, if there is no match
        // between updatedBook's genre and existing book's genre, then delete its book info from genreSet
        genreSet.forEach(genre -> {
            if (!updateBookDTO.getGenre().contains(genre.getName())) {
                genre.getBookSet().remove(bookCopy);
            }
        });

        // for each genre in updateBook, try to locate if genre already exists in genre repository
        updateBookDTO.getGenre().forEach(genreName -> {
            Genre genre = genreRepository.findByName(genreName);
            // if the genre already exists in a repository, add it into Genre object's set of books.
            if (genre != null) {
                genre.getBookSet().add(bookCopy);
            } else {
                // if the genre does not exist, update it with the new book
                genre = new Genre();
                genre.setName(genreName);
                genre.getBookSet().add(bookCopy);
                genreRepository.save(genre);
            }
            // add genre to its set
            genreSet.add(genre);

        });
        // set its genre
        book.setGenreSet(genreSet);

        // 3. update other fields
        book.setTitle(updateBookDTO.getTitle());
        book.setPublished(updateBookDTO.getPublished());
        book.setPages(updateBookDTO.getPages());

        try {
            // save the updated changes
            book = bookRepository.save(book);
        } catch (Exception e) {
            // throw an exception if there is any issue
            throw new ValidationException();
        }

        // Convert updateBookDTO to GetBookDTO object
        GetBookDTO getBookDTO = mapper.map(updateBookDTO, GetBookDTO.class);

        // properly update other DTO attributes
        getBookDTO.setPages(updateBookDTO.getPages());
        getBookDTO.setPublished(updateBookDTO.getPublished());
        getBookDTO.setTitle(updateBookDTO.getTitle());
        getBookDTO.setGenres(updateBookDTO.getGenre());
        getBookDTO.setAuthor(updateBookDTO.getAuthor());

        // return getBookDTO
        return getBookDTO;
    }

    // When a book is deleted, also update Genre object
    public void deleteById(Long id) {
        // if book is not found by its id from repository, throw an error
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book not Found"));
        // remove the book from Book object's genre set
        book.getGenreSet().forEach(genre -> {
            genre.getBookSet().remove(book);
        });
        // finally delete a book from the book repository
        bookRepository.deleteById(id);
    }
}
