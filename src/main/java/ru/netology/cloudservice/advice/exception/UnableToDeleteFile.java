package ru.netology.cloudservice.advice.exception;

public class UnableToDeleteFile extends RuntimeException {
    public UnableToDeleteFile(String message) {
        super(message);
    }
}