package com.br.brangioni.java.springboot2.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
   protected String tittle;
   protected int status;
   protected String details;
   protected String developerMessage;
   protected LocalDateTime timestamp;
}
