package com.example.demofilestoreservice.domain.exception;

public class FileAlreadyExistsException  extends RuntimeException {

    public FileAlreadyExistsException(String fileName) {

        super("the file " + fileName + " already exists");
    }

}
