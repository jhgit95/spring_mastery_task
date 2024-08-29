package com.sparta.spring_mastery_task.dto;

import com.sparta.spring_mastery_task.entity.Assignee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssigneeDto {
    private int userId;
    private int assigneeId;
    private String userName;
    private String userEmail;

    public AssigneeDto(Assignee assignee) {
        this.assigneeId = assignee.getAssigneeId();
        this.userId = assignee.getUser().getUserId();
        this.userName = assignee.getUser().getUserName();
        this.userEmail=assignee.getUser().getEmail();
    }
}
