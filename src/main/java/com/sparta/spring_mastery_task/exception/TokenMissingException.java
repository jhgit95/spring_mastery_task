package com.sparta.spring_mastery_task.exception;


public class TokenMissingException extends RuntimeException {
    // 만료된 토큰
    public TokenMissingException(String message){
        super(message);
    }
}