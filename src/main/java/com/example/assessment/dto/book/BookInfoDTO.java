package com.example.assessment.dto.book;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookInfoDTO {
    private String title;
    private int pages;
    private String author;
}
