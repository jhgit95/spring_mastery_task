package com.sparta.spring_mastery_task.service;

import com.sparta.spring_mastery_task.dto.assgineeCreateDto.AssigneeCreateDtoRequest;
import com.sparta.spring_mastery_task.entity.Assignee;
import com.sparta.spring_mastery_task.repository.AssigneeRepository;
import com.sparta.spring_mastery_task.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssigneeService {

    private final AssigneeRepository assigneeRepository;
    private final ScheduleRepository scheduleRepository;

    public Assignee createAssignee(AssigneeCreateDtoRequest reqDto) {
        Assignee assignee = new Assignee(reqDto);
        return assigneeRepository.save(assignee);


    }

}
