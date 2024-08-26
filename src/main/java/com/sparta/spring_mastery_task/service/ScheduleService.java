package com.sparta.spring_mastery_task.service;


import com.sparta.spring_mastery_task.dto.AssigneeDto;
import com.sparta.spring_mastery_task.dto.ScheduleGetDto.ScheduleGetDtoResponse;
import com.sparta.spring_mastery_task.dto.ScheduleSaveDto.ScheduleSaveDtoRequest;
import com.sparta.spring_mastery_task.dto.ScheduleSaveDto.ScheduleSaveDtoResponse;
import com.sparta.spring_mastery_task.dto.ScheduleUpdateDto.ScheduleUpdateDtoResponse;
import com.sparta.spring_mastery_task.entity.Assignee;
import com.sparta.spring_mastery_task.entity.Schedule;
import com.sparta.spring_mastery_task.repository.AssigneeRepository;
import com.sparta.spring_mastery_task.repository.ScheduleRepository;
import com.sparta.spring_mastery_task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssigneeRepository assigneeRepository;

    @Transactional
    // 일정 저장
    public ScheduleSaveDtoResponse saveSchedule(ScheduleSaveDtoRequest reqDto) {
        Schedule schedule = new Schedule(reqDto);
         Schedule newSchedule = scheduleRepository.save(schedule);
        Assignee assignee = new Assignee();
        assignee.setSchedule(newSchedule);
        assignee.setUser(reqDto.getUser());
        assignee.setWriterId(reqDto.getWriterId());
        assigneeRepository.save(assignee);

        ScheduleSaveDtoResponse resDto = new ScheduleSaveDtoResponse(assignee);
        return resDto;


    }

    // 단건 조회
    public ScheduleGetDtoResponse getScheduleById(int id) {
        ScheduleGetDtoResponse resDto = new ScheduleGetDtoResponse(scheduleRepository.findById(id).orElse(null));

        // Assignee 목록을 가져옵니다
        List<Assignee> assignees = assigneeRepository.findBySchedule_ScheduleId(id);

        // AssigneeDto 리스트를 생성합니다
        List<AssigneeDto> assigneeDtos = assignees.stream()
                .map(assignee -> {
                    AssigneeDto dto = new AssigneeDto();
                    dto.setUserId(assignee.getUser().getUserId());
                    return dto;
                })
                .collect(Collectors.toList());


        resDto.setAssignees(assigneeDtos);

        return resDto;


    }

    @Transactional
    // 일정 수정
    public ScheduleUpdateDtoResponse updateSchedule(int id, Schedule updatedSchedule) {
        // 요청받은 스케쥴id, 유저id 존재 확인
        // assignee 추가해서 잠시 주석 처리
//        if (scheduleRepository.existsById(id) && userRepository.existsById(updatedSchedule.getUser().getUserId())) {
        if (scheduleRepository.existsById(updatedSchedule.getScheduleId()) ) {
            ScheduleUpdateDtoResponse resDto = new ScheduleUpdateDtoResponse(scheduleRepository.save(updatedSchedule));

            System.out.println("뭔데 스케쥴 아이디"+updatedSchedule.getScheduleId());
            resDto.setAssignees(assigneeRepository.findBySchedule_ScheduleId(updatedSchedule.getScheduleId()));
//            updatedSchedule.setScheduleId(id);
            System.out.println("뭔데 담당자 "+resDto.getAssignees());
            return resDto;
        }
        return null; // 또는 예외를 던질 수 있습니다.
    }

//    // 페이징
//    // 스케쥴 제목, 내용, 작성일 수정일, 작성 유저명, 댓글 수 조회
//    public Page<SchedulePagingResponseDto> getSchedules(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//
//        return scheduleRepository.findSchedulesWithDetails(pageable);
////        SchedulePagingResponseDto pagingResponseDto = new SchedulePagingResponseDto();
////        return scheduleRepository.findAll(pageable);
//
//
//    }

    @Transactional
    public void deleteSchedule(int id){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 스케줄이 존재하지 않습니다. ID: " + id));

        scheduleRepository.delete(schedule);
    }
}