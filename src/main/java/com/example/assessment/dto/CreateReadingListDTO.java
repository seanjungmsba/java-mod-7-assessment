package com.example.assessment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateReadingListDTO {
    private String name;
    private String username;
    private String password;
    private String title;
    private int pages;
    private Date published;
}

/*
GET /api/users/{id}/reading_lists	Gets the given user’s reading lists.
POST /api/users/{id}/reading_lists	Create a new reading list for the user with the given ID.
GET /api/users/{id}/reading_lists/{list_id}	Gets the given user’s reading list with the ID list_id.
{
"name": "ReadingListName1"
}
 */
