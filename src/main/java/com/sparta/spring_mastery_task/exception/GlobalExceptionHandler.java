package com.sparta.spring_mastery_task.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 요청된 바디가 잘못된 경우
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> BadRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EmailPwException.class)
    public ResponseEntity<String> EmailPwException(EmailPwException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(TokenExpireException.class)
    public ResponseEntity<String> TokenExpireException(TokenExpireException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(TokenMissingException.class)
    public ResponseEntity<String> TokenMissingException(TokenMissingException ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> ConflictException(ConflictException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> ForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(403).body(ex.getMessage());
    }

}
