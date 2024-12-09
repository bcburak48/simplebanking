package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.InsufficientBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<TransactionStatus> handleInsufficientBalance(InsufficientBalanceException ex) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TransactionStatus("FAILED", null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<TransactionStatus> handleGenericException(Exception ex) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TransactionStatus("FAILED", null));
    }
}
