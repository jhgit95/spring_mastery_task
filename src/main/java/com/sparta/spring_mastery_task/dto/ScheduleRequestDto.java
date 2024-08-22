package com.sparta.spring_mastery_task.dto;

import com.sparta.spring_mastery_task.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequestDto {

    private int scheduleId;
    private String title;
    private String content;
    private String regDate;
    private String modDate;
    private User user;

}
