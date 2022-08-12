package com.example.assessment.dto.readinglist;

import com.example.assessment.dto.book.BookInfoDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CreateReadingListDTO {
    private String readingListName;
    private String username;
    private List<BookInfoDTO> readingList;
}

/*
GET /api/users/{id}/reading_lists	Gets the given user’s reading lists.
GET /api/users/{id}/reading_lists/{list_id}	Gets the given user’s reading list with the ID list_id.

POST /api/users/{id}/reading_lists	Create a new reading list for the user with the given ID.
when posting, make sure username is already in the UserRepository
{
"readingListName": "ReadingListName1",
"username": "UserNameA",
"readingList":
[{"title": "titleA", "pages": 13, "author":"AuthorA"},
{"title": "titleB", "pages": 32, "author":"AuthorB"}]
}

 */