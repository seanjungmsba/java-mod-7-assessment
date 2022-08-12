package com.example.assessment.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GetBookDTO {
    private long id;
    private String title;
    private String author;
    private List<String> genres;
    private int pages;
    private Date published;
}
