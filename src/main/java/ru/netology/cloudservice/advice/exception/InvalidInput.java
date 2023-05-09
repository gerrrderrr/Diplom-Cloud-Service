package ru.netology.cloudservice.advice.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class InvalidInput extends MethodArgumentNotValidException {
    public InvalidInput(MethodParameter parameter, BindingResult bindingResult) {
        super(parameter, bindingResult);
    }
}