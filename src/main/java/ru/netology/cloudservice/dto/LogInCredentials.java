package ru.netology.cloudservice.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LogInCredentials {
    private String login;
    @Size(min = 10)
    private String password;
}
