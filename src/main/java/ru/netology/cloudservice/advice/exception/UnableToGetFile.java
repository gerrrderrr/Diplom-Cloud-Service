package ru.netology.cloudservice.advice.exception;

public class UnableToGetFile extends RuntimeException {
    public UnableToGetFile(String message) {
        super(message);
    }

    public UnableToGetFile() {
    }
}