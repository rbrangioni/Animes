package com.br.brangioni.java.springboot2.handler;

import com.br.brangioni.java.springboot2.exception.BadRequestException;
import com.br.brangioni.java.springboot2.exception.BadRequestExceptionDetails;
import com.br.brangioni.java.springboot2.exception.ExceptionDetails;
import com.br.brangioni.java.springboot2.exception.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .tittle("Bad Rquest Exception, verifique a documentação!")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<ValidationExceptionDetails> handlerBMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
     @Override
     protected ResponseEntity<Object> handleMethodArgumentNotValid(
             MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessages = fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .tittle("Bad Rquest Exception, Campo Inválido!")
                        .details("Erro na Validação dos Campos:")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldMessage(fieldMessages)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .tittle(ex.getCause().getMessage())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }


}
