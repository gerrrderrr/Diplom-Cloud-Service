package ru.netology.cloudservice.advice.exception;

public class UnableToUploadFile extends RuntimeException {
    public UnableToUploadFile(String message) {
        super(message);
    }
}