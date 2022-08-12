package com.example.assessment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
}

/*
POST api/users
DELETE api/users/{id}

{
    "username": "username1",
    "password": "PassWord1234@"
}
{
    "username": "username2",
    "password": "PassWord4567!"
}
 */