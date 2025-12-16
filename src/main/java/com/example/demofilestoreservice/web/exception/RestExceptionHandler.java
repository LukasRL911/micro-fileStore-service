package com.example.demofilestoreservice.web.exception;

import com.example.demofilestoreservice.domain.exception.FileAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends RuntimeException {


    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<Error> handleException(FileAlreadyExistsException ex) {

        Error error = new Error("file-already-exists", ex.getMessage());
        return ResponseEntity.badRequest().body(error);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {

        List<Error> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errors.add(new Error(error.getField(), error.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errors);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {

        Error error = new Error("unknown-error", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

}
