package com.example.assessment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GetBookDTO {
    private Long id;
    private String title;
    //    private String author;
    private int pages;
    private Date published;
}
