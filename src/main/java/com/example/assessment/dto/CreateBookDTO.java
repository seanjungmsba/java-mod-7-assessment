package com.example.assessment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
public class CreateBookDTO {
    private Long id;
    private String title;
    private int pages;
    private Date published;
}

/*
POST /api/books
{
    "id":1,
    "title":"RandomBookTitle1",
    "pages": 32,
    "published": "2001-09-24"
}
{
    "id":2,
    "title":"RandomBookTitle2",
    "pages": 55,
    "published": "1998-04-23"
}
 */
