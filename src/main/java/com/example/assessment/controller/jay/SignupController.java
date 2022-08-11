package com.example.assessment.controller.jay;

import com.example.assessment.dto.jay.ActivityDTO;
import com.example.assessment.dto.jay.CreateSignUpDTO;
import com.example.assessment.service.jay.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("signups")
public class SignupController {
    @Autowired
    private SignupService signupService;

    @PostMapping
    public ActivityDTO createSignup(@Valid @RequestBody CreateSignUpDTO createSignUpDTO) {
        return signupService.create(createSignUpDTO);
    }
}
