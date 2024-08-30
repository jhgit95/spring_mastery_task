package com.sparta.spring_mastery_task.controller;

import com.sparta.spring_mastery_task.dto.assgineeCreateDto.AssigneeCreateDtoRequest;
import com.sparta.spring_mastery_task.entity.Assignee;
import com.sparta.spring_mastery_task.service.AssigneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assignee")
@RequiredArgsConstructor
public class AssigneeController {
    private final AssigneeService assigneeService;

    // 일정을 작성한 유저가 해당 일정의 담당자를 지정하는 기능
    @PostMapping
    public ResponseEntity<Assignee> createAssignee(@RequestBody AssigneeCreateDtoRequest reqDto){
        return ResponseEntity.ok(assigneeService.createAssignee(reqDto));
    }
}
