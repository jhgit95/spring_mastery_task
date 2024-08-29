package com.sparta.spring_mastery_task.exception;

public class EmailPwException extends RuntimeException {
    // 로그인 이메일과 비밀번호 일치하지 않은 경우
    public EmailPwException(String message){
        super(message);
    }
}

