package com.example.assessment.dto.error;

import lombok.Data;

import java.util.Set;

@Data
public class ErrorsDTO {
    private Set<String> errors;
}
