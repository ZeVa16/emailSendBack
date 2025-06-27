package com.example.sendEmail.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MissingFieldsException extends RuntimeException {
    final HttpStatus status = HttpStatus.BAD_REQUEST;
    public MissingFieldsException(String message) {
        super(message);
    }

}
