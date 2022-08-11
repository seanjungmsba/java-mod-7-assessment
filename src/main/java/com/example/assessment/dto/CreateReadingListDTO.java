package com.example.assessment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateReadingListDTO {
    private Long id;
    private String name;
}

/*
GET /api/users/{id}/reading_lists	Gets the given user’s reading lists.
POST /api/users/{id}/reading_lists	Create a new reading list for the user with the given ID.
GET /api/users/{id}/reading_lists/{list_id}	Gets the given user’s reading list with the ID list_id.
{
"name": "ReadingListName1"
}
 */
