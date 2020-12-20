package com.br.brangioni.java.springboot2.handler;

import com.br.brangioni.java.springboot2.exception.BadRequestException;
import com.br.brangioni.java.springboot2.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .tittle("Bad Rquest Exception, verifique a documentação!")
                .details(badRequestException.getMessage())
                .developerMessage(badRequestException.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

}
