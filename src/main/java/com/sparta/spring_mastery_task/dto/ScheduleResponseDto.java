package com.sparta.spring_mastery_task.dto;

import com.sparta.spring_mastery_task.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleResponseDto {

    private int scheduleId;
    private String title;
    private String content;
    private String regDate;
    private String modDate;

    private int userId;


    public ScheduleResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.regDate = schedule.getRegDate();
        this.modDate = schedule.getModDate();
        this.userId = schedule.getUser().getUserId();
    }


}
