package com.example.assessment.dto.readinglist;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class GetReadingListDTO {
    private Long id;
    private String name;
}
