package com.example.assessment.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GetUserDTO {
    private Long id;
    private String username;
}
