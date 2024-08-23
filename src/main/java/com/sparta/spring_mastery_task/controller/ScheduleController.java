package com.sparta.spring_mastery_task.controller;

import com.sparta.spring_mastery_task.dto.ScheduleRequestDto;
import com.sparta.spring_mastery_task.dto.ScheduleResponseDto;
import com.sparta.spring_mastery_task.entity.Schedule;
import com.sparta.spring_mastery_task.entity.User;
import com.sparta.spring_mastery_task.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    // 일정 저장
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto reqDto) {
        Schedule schedule = new Schedule(reqDto);

        ScheduleResponseDto resDto = new ScheduleResponseDto(scheduleService.saveSchedule(schedule));

        return ResponseEntity.ok(resDto);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable int id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        ScheduleResponseDto resDto = new ScheduleResponseDto(schedule);
//        return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.ok(resDto);
        // 예외처리 필요

    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable int id, @RequestBody ScheduleRequestDto reqDto) {
        reqDto.setScheduleId(id);
        Schedule schedule = new Schedule(reqDto);
        ScheduleResponseDto resDto = new ScheduleResponseDto(scheduleService.updateSchedule(id, schedule));
//        return resDto != null ? ResponseEntity.ok(schedule) : ResponseEntity.notFound().build();
        return ResponseEntity.ok(resDto);
    }

    // 페이징
    public void paginationSchedule(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {

    }


}