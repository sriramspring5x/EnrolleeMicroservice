package com.enrollmentservice.AddEnrollee.Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class EnrolleeNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(EnrolleeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EnrolleeNotFoundException ex) {
        return ex.getMessage();
    }
}