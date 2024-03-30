package com.example.Sales.Management.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;
import java.util.ArrayList;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ExceptionFormat> handleRecordNotFound(RecordNotFoundException recordNotFoundEcxeption)
    {
        return new ResponseEntity<>(
                ExceptionFormat.builder()
                        .message(recordNotFoundEcxeption.getMessage())
                        .status(false)
                        .time(LocalTime.now())
                        .details(new ArrayList<>())
                        .build()
                , HttpStatus.NOT_FOUND);
    }
}
