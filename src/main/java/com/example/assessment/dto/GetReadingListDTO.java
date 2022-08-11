package com.example.assessment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GetReadingListDTO {
    private Long id;
    private String name;
    private List<CreateBookDTO> readingList;
}
