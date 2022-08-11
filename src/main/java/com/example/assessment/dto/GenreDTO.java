package com.example.assessment.dto;

import lombok.Data;

@Data
public class GenreDTO {

    private Long id;

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