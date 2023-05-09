package ru.netology.cloudservice.util.validator;

import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.cloudservice.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameIsUniqueValidator implements ConstraintValidator<UsernameIsUnique, String> {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsUserByUsername(username);
    }
}
