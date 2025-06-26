package com.example.sendEmail.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class ApiResponse<T> {

    private String message;
    private T data;
    private HttpStatus status;
}
