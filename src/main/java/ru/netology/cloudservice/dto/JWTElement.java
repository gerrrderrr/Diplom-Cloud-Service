package ru.netology.cloudservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JWTElement {
    @JsonProperty("auth-token")
    private String jwt;
}
