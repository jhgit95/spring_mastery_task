package com.sparta.spring_mastery_task.dto.loginDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDtoRequest {

    private String email;
    private String pw;


}
