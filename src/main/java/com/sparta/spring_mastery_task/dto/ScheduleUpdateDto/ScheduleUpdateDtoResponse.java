package com.sparta.spring_mastery_task.dto.ScheduleUpdateDto;

import com.sparta.spring_mastery_task.dto.AssigneeDto;
import com.sparta.spring_mastery_task.entity.Assignee;
import com.sparta.spring_mastery_task.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleUpdateDtoResponse {

    private int scheduleId;
    private String title;
    private String content;
    private String regDate;
    private String modDate;

    private int userId;

    private List<AssigneeDto> assignees;


    public ScheduleUpdateDtoResponse(Schedule schedule) {
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.regDate = schedule.getRegDate();
        this.modDate = schedule.getModDate();
    }

    // 무한 순환 방지
    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees.stream()
                .map(AssigneeDto::new)
                .collect(Collectors.toList());
    }


}
