package com.example.assessment.repository;

import com.example.assessment.model.Author;
import com.example.assessment.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleAndAuthor(String title, Author author);
}
