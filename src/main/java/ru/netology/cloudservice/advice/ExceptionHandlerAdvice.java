package ru.netology.cloudservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.cloudservice.advice.exception.*;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(UnableToSignUp.class)
    public ResponseEntity<String> unableToSignUp(UnableToSignUp e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnableToCreateFolder.class)
    public ResponseEntity<String> unableToCreateFolder(UnableToCreateFolder e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnableToGetFile.class)
    public ResponseEntity<String> unableToGetFile(UnableToGetFile e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnableToUploadFile.class)
    public ResponseEntity<String> unableToUploadFile(UnableToUploadFile e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnableToUpdateFile.class)
    public ResponseEntity<String> unableToUpdateFile(UnableToUpdateFile e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnableToDeleteFile.class)
    public ResponseEntity<String> unableToDeleteFile(UnableToDeleteFile e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInput.class)
    public ResponseEntity<String> invalidInput(InvalidInput e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unauthorizedUser(UnauthorizedUser e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
