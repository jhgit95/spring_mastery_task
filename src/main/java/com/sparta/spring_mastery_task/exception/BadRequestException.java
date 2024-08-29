package com.sparta.spring_mastery_task.exception;

public class BadRequestException extends RuntimeException {

    // 요청 바디가 잘못된 경우
    public BadRequestException(String message) {
        super(message);
    }


}





