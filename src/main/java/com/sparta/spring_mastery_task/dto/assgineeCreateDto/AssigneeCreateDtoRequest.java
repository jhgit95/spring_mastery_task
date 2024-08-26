package com.sparta.spring_mastery_task.dto.assgineeCreateDto;

import com.sparta.spring_mastery_task.entity.Schedule;
import com.sparta.spring_mastery_task.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssigneeCreateDtoRequest {

//    private int assigneeId;
//    private int scheduleId;
//    private int userId;
    private int writerId;
    private User user;
    private Schedule schedule;




}
