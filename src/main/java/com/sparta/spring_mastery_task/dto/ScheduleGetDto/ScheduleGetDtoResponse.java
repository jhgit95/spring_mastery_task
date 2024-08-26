package com.sparta.spring_mastery_task.dto.ScheduleGetDto;

import com.sparta.spring_mastery_task.dto.AssigneeDto;
import com.sparta.spring_mastery_task.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleGetDtoResponse {
    private int scheduleId;
    private String title;
    private String content;
    private String regDate;
    private String modDate;

//    private int userId;
//    private String userName;
//    private String userEmail;

//    private List<Assignee> assignees;

    private List<AssigneeDto> assignees;

    public ScheduleGetDtoResponse(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.regDate = schedule.getRegDate();
        this.modDate = schedule.getModDate();
    }

//    public ScheduleGetDtoResponse(List<Assignee> assignees){
//        this.assignees=assignees;
//    }
//    public void addAssignees(List<Assignee> assignees){
//        this.assignees=assignees;
//    }
//    public void setAssignees(List<Assignee> assignees) {
//        this.assignees = assignees.stream()
//                .map(AssigneeDto::new)
//                .collect(Collectors.toList());
//    }
}
