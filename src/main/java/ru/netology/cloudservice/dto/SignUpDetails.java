package ru.netology.cloudservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.netology.cloudservice.util.validator.UsernameIsUnique;

@Getter
@AllArgsConstructor
public class SignUpDetails {
    @UsernameIsUnique
    private String username;
    private String name;
    private String lastname;
    private int age;
    private String email;
    private String password;
}
