package com.sparta.spring_mastery_task.dto;

import com.sparta.spring_mastery_task.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SchedulePagingResponseDto {

    private int scheduleId;
    private String title;
    private String content;
    private String regDate;
    private String modDate;

    private int userId;

    private String userName;
    private long commentCount;

    public SchedulePagingResponseDto(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.regDate = schedule.getRegDate();
        this.modDate = schedule.getModDate();
        this.userId = schedule.getUser().getUserId();
    }
    // 추가적인 기본 생성자와 getter, setter 필요시 정의
    public SchedulePagingResponseDto(int scheduleId, String title, String content, String regDate, String modDate, String userName,int userId, long commentCount) {
        this.scheduleId = scheduleId;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.modDate = modDate;
        this.userName = userName;
        this.userId=userId;
        this.commentCount = commentCount;
    }



}
