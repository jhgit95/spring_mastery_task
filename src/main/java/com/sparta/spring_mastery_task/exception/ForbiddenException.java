package com.sparta.spring_mastery_task.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message){
        super(message);
    }
}
