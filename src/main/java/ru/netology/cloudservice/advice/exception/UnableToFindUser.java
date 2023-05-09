package ru.netology.cloudservice.advice.exception;

public class UnableToFindUser extends RuntimeException {
    public UnableToFindUser(String message) {
        super(message);
    }

    public UnableToFindUser() {
    }
}