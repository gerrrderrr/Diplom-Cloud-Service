package ru.netology.cloudservice.advice.exception;

public class UnableToSignUp extends RuntimeException {
    public UnableToSignUp(String message) {
        super(message);
    }
}