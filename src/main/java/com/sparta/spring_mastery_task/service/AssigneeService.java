package com.sparta.spring_mastery_task.service;

import com.sparta.spring_mastery_task.dto.assgineeCreateDto.AssigneeCreateDtoRequest;
import com.sparta.spring_mastery_task.entity.Assignee;
import com.sparta.spring_mastery_task.entity.Schedule;
import com.sparta.spring_mastery_task.repository.AssigneeRepository;
import com.sparta.spring_mastery_task.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssigneeService {

    @Autowired
    private AssigneeRepository assigneeRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    public Assignee createAssignee(AssigneeCreateDtoRequest reqDto){
        Assignee assignee = new Assignee(reqDto);
        return assigneeRepository.save(assignee);



    }

}
