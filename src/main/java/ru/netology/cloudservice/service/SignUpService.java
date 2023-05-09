package ru.netology.cloudservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.cloudservice.advice.exception.UnableToSignUp;
import ru.netology.cloudservice.dto.SignUpDetails;
import ru.netology.cloudservice.entity.User;
import ru.netology.cloudservice.repository.UserRepository;
import ru.netology.cloudservice.util.UserBuilder;

@Service
public class SignUpService {
    private final UserRepository repository;

    public SignUpService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public String signUp(SignUpDetails details) {
        UserBuilder builder = new UserBuilder();
        User user = builder.buildUser(details);
        repository.save(user);
        String username = user.getUsername();
        if (userExistsInDB(username)) {
            return "successfully_signed_up";
        } else {
            throw new UnableToSignUp("Wasn't able to sign up");
        }
    }

    private boolean userExistsInDB(String username) {
        return repository.existsUserByUsername(username);
    }
}