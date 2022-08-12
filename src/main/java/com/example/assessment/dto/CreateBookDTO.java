package com.example.assessment.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateBookDTO {
    private String title;
    private String author;
    private List<String> genre;
    private int pages;
    private Date published;
}

/*
POST /api/books
{
    "title":"RandomBookTitle1",
    "author":"AuthorNameA",
    "genre": ["Genre1", "Genre2"],
    "pages": 64,
    "published": "2008-07-11"
}
 */
