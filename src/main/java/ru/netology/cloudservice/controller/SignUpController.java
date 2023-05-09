package ru.netology.cloudservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.netology.cloudservice.dto.SignUpDetails;
import ru.netology.cloudservice.service.SignUpService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class SignUpController {
    private SignUpService service;

    @GetMapping("/signup")
    public String signUp() {
        return "sign_up";
    }

    @PostMapping("/signup")
    public String signUp(@Valid SignUpDetails details) {
        return service.signUp(details);
    }
}
