package com.techmaster.carrepairsystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Data
public class ExceptionResponse {
    private HttpStatus httpStatus;
    private String message;
}
