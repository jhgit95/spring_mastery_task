package com.sparta.spring_mastery_task.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 요청된 바디가 잘못된 경우
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleMissingFieldException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EmailPwException.class)
    public ResponseEntity<String> EmailPwException(BadRequestException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(TokenExpireException.class)
    public ResponseEntity<String> TokenExpireException(BadRequestException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(TokenMissingException.class)
    public ResponseEntity<String> TokenMissingException(BadRequestException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

}
