package com.example.assessment.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetGenreDTO {
    private Long id;
    private String name;
    private List<GetBookDTO> books;
}
