package com.neoage.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class EmployeeManagementExceptionHandler {

    @ExceptionHandler(EmployeeManagementException.class)
    public ProblemDetail handleException(EmployeeManagementException exception) {
        log.error(exception.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}
