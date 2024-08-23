package com.sparta.spring_mastery_task.dto;

import com.sparta.spring_mastery_task.entity.Schedule;
import com.sparta.spring_mastery_task.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDto {
    int commentId;
    String content;
    String regDate;
    String modDate;
    User user;
    Schedule schedule;
}
