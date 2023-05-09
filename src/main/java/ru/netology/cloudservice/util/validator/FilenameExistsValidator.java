package ru.netology.cloudservice.util.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.netology.cloudservice.entity.FileHolder;
import ru.netology.cloudservice.repository.FilesRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.security.Principal;
import java.util.Optional;

public class FilenameExistsValidator implements ConstraintValidator<FilenameExists, String> {
    @Autowired
    FilesRepository repository;

    @Override
    public boolean isValid(String filename, ConstraintValidatorContext constraintValidatorContext) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<FileHolder> holder = repository.getFileByFilenameAndUsername(filename, username);
        return holder.isPresent();
    }
}