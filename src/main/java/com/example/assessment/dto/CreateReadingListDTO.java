package com.example.assessment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateReadingListDTO {
    private Long id;
    private String name;
}
