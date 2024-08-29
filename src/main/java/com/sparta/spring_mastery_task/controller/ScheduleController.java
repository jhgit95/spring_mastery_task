package com.sparta.spring_mastery_task.controller;

import com.sparta.spring_mastery_task.dto.ScheduleGetDto.ScheduleGetDtoResponse;
import com.sparta.spring_mastery_task.dto.SchedulePagingResponseDto;
import com.sparta.spring_mastery_task.dto.ScheduleRequestDto;
import com.sparta.spring_mastery_task.dto.ScheduleSaveDto.ScheduleSaveDtoRequest;
import com.sparta.spring_mastery_task.dto.ScheduleSaveDto.ScheduleSaveDtoResponse;
import com.sparta.spring_mastery_task.dto.ScheduleUpdateDto.ScheduleUpdateDtoResponse;
import com.sparta.spring_mastery_task.dto.scheduleGetAllDto.ScheduleGetAllDtoResponse;
import com.sparta.spring_mastery_task.entity.Schedule;
import com.sparta.spring_mastery_task.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 저장
    // dto 수정 필요
    // 담당자 스케쥴 유저 객체 -> id만 받기
    @PostMapping
    public ResponseEntity<ScheduleSaveDtoResponse> createSchedule(@RequestBody ScheduleSaveDtoRequest reqDto) {
        ScheduleSaveDtoResponse resDto = scheduleService.saveSchedule(reqDto);
        return ResponseEntity.ok(resDto);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleGetDtoResponse> getSchedule(@PathVariable int id) {
        ScheduleGetDtoResponse resDto = scheduleService.getScheduleById(id);

//        return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.ok(resDto);
        // 예외처리 필요

    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleUpdateDtoResponse> updateSchedule(@PathVariable int id, @RequestBody ScheduleRequestDto reqDto) {
        reqDto.setScheduleId(id);
        Schedule schedule = new Schedule(reqDto);
        ScheduleUpdateDtoResponse resDto;
        resDto = scheduleService.updateSchedule(id, schedule);
//        return resDto != null ? ResponseEntity.ok(schedule) : ResponseEntity.notFound().build();
        return ResponseEntity.ok(resDto);
    }

    //    // 페이징
    @GetMapping("/schedules")
    public ResponseEntity<Page<SchedulePagingResponseDto>> getAllSchedules(
            @RequestParam(required = false, defaultValue = "0") int page,  // 기본 페이지 번호는 0
            @RequestParam(required = false, defaultValue = "10") int size  // 기본 페이지 크기는 10
    ) {
        return ResponseEntity.ok(scheduleService.getSchedules(page, size));
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable int id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok("삭제 완료");
    }

    // 그냥 dto에 담아서 보낸 것 같은데, 이게 지연 로딩이 된 건가?
    @GetMapping("/all")
    public ResponseEntity<List<ScheduleGetAllDtoResponse>> getAllSchedule() {
        return ResponseEntity.ok(scheduleService.getAllSchedule());

    }


}