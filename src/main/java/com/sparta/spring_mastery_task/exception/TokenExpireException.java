package com.sparta.spring_mastery_task.exception;



public class TokenExpireException extends RuntimeException {
    // 만료된 토큰
    public TokenExpireException(String message){
        super(message);
    }
}