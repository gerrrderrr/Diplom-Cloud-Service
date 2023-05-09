package ru.netology.cloudservice.advice.exception;

public class UnableToUpdateFile extends RuntimeException {
    public UnableToUpdateFile(String message) {
        super(message);
    }
}