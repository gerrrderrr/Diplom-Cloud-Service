package ru.netology.cloudservice.util;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.netology.cloudservice.dto.SignUpDetails;
import ru.netology.cloudservice.entity.Role;
import ru.netology.cloudservice.entity.User;

@NoArgsConstructor
public class UserBuilder {
    public User buildUser(SignUpDetails details) {
        return User.builder().username(details.getUsername())
                .name(details.getName())
                .lastname(details.getLastname())
                .age(details.getAge())
                .email(details.getEmail())
                .role(Role.USER)
                .password(encodePassword(details.getPassword()))
                .build();
    }

    private String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
