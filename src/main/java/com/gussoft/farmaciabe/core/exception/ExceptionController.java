package com.gussoft.farmaciabe.core.exception;

import com.gussoft.farmaciabe.core.utils.Util;
import com.gussoft.farmaciabe.integration.transfer.response.ExceptionResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(Exception e) {
        ExceptionResponse error = new ExceptionResponse(
                Util.getFormatDate(LocalDateTime.now()),
                HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    public ResponseEntity<ExceptionResponse> invalidExceptionHandler(RuntimeException e) {
        ExceptionResponse error = new ExceptionResponse(
                Util.getFormatDate(LocalDateTime.now()),
                HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FileException.class)
    public ResponseEntity<ExceptionResponse> invalidExceptionHandler(Exception e) {
        ExceptionResponse error = new ExceptionResponse(
                Util.getFormatDate(LocalDateTime.now()),
                HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
