package com.example.assessment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserDTO {
//    private Long id;
    private String username;
    private String password;
}

/*
{
    "id": 1,
    "username": "username1",
    "password": "PassWord1234@"
}

{
    "id": 2,
    "username": "username2",
    "password": "PassWord4567!"
}

POST api/users
DELETE api/users/{id}
 */