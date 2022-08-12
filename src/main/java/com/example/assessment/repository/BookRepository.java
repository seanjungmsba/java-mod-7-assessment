package com.example.assessment.repository;

import com.example.assessment.model.Author;
import com.example.assessment.model.Book;
import com.example.assessment.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByTitleAndAuthor(String title, Author author);
}
