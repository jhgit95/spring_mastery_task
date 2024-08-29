package com.sparta.spring_mastery_task.dto.userSaveDto;

import com.sparta.spring_mastery_task.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSaveDtoResponse {
    private int userId;
    private String userName;


    private String email;

    private String regDate;

    private String modDate;

    private String auth;

    public UserSaveDtoResponse(User user){
        this.userId=user.getUserId();
        this.userName=user.getUserName();
        this.email=user.getEmail();
        this.regDate=user.getRegDate();
        this.modDate=user.getModDate();
        this.auth=user.getAuth();

    }


}
