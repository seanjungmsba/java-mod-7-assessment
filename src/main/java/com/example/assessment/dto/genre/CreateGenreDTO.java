package com.example.assessment.dto.genre;

import lombok.Data;

@Data
public class CreateGenreDTO {

//    private Long id;

    private String name;
}

/*
POST /api/genre
GET /api/genre/{id}/books
{
    "id":1,
    "name":"RandomGenre1"
}
{
    "id":2,
    "name":"RandomGenre2"
}
 */