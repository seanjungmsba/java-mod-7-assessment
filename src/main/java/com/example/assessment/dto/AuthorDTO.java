package com.example.assessment.dto;

import lombok.Data;

@Data
public class AuthorDTO {
    private Long id;
    private String name;
}

/*
POST /api/author
GET /api/author
{
    "id": 1,
    "name": "JK Rollins"
}
{
    "id": 2,
    "name": "Random Author"
}
 */