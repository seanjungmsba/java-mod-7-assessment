package com.example.assessment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GetReadingListDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String title;
    private int pages;
    private Date published;
}
