package ru.netology.cloudservice.advice.exception;

public class UnableToCreateFolder extends RuntimeException {
    public UnableToCreateFolder(String message) {
        super(message);
    }
}