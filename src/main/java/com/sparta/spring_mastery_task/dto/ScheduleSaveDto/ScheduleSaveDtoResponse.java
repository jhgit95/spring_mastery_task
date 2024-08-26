package com.sparta.spring_mastery_task.dto.ScheduleSaveDto;

import com.sparta.spring_mastery_task.entity.Assignee;
import com.sparta.spring_mastery_task.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleSaveDtoResponse {
    private int scheduleId;
    private String title;
    private String content;
    private String regDate;
    private String modDate;
    private User user;
    private Assignee assignee;

    public ScheduleSaveDtoResponse(Assignee assignee){
        this.scheduleId=assignee.getSchedule().getScheduleId();
        this.title=assignee.getSchedule().getTitle();
        this.content=assignee.getSchedule().getContent();
        this.regDate=assignee.getSchedule().getRegDate();
        this.modDate=assignee.getSchedule().getModDate();
        this.user=assignee.getUser();
        this.assignee=assignee;

    }

}
