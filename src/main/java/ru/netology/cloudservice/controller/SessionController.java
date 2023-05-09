package ru.netology.cloudservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.cloudservice.dto.JWTElement;
import ru.netology.cloudservice.dto.LogInCredentials;
import ru.netology.cloudservice.service.SessionService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class SessionController {
    private final SessionService service;

    @PostMapping("/login")
    public ResponseEntity<JWTElement> logIn(@RequestBody @Valid LogInCredentials credentials) {
        return service.logIn(credentials);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut() {
        return service.logOut();
    }
}
