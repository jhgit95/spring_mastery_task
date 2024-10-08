package com.sparta.spring_mastery_task.service;


import com.sparta.spring_mastery_task.dto.AssigneeDto;
import com.sparta.spring_mastery_task.dto.ScheduleGetDto.ScheduleGetDtoResponse;
import com.sparta.spring_mastery_task.dto.SchedulePagingResponseDto;
import com.sparta.spring_mastery_task.dto.ScheduleSaveDto.ScheduleSaveDtoRequest;
import com.sparta.spring_mastery_task.dto.ScheduleSaveDto.ScheduleSaveDtoResponse;
import com.sparta.spring_mastery_task.dto.ScheduleUpdateDto.ScheduleUpdateDtoResponse;
import com.sparta.spring_mastery_task.dto.scheduleGetAllDto.ScheduleGetAllDtoResponse;
import com.sparta.spring_mastery_task.entity.Assignee;
import com.sparta.spring_mastery_task.entity.Schedule;
import com.sparta.spring_mastery_task.exception.BadRequestException;
import com.sparta.spring_mastery_task.repository.AssigneeRepository;
import com.sparta.spring_mastery_task.repository.ScheduleRepository;
import com.sparta.spring_mastery_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final AssigneeRepository assigneeRepository;

    @Transactional
    // 일정 저장
    public ScheduleSaveDtoResponse saveSchedule(ScheduleSaveDtoRequest reqDto) {
        Schedule schedule = new Schedule(reqDto);
         Schedule newSchedule = scheduleRepository.save(schedule);
        Assignee assignee = new Assignee();
        assignee.setSchedule(newSchedule);
        assignee.setUser(reqDto.getUser());
//        assignee.setWriterId(reqDto.getWriterId());
        assigneeRepository.save(assignee);

        ScheduleSaveDtoResponse resDto = new ScheduleSaveDtoResponse(assignee);
        return resDto;


    }

    // 단건 조회
    @Transactional
    public ScheduleGetDtoResponse getScheduleById(int id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new BadRequestException("존재하지 않는 schedule_id"));
        ScheduleGetDtoResponse resDto = new ScheduleGetDtoResponse(schedule);

        System.out.println("전");
        // Assignee 목록을 가져옵니다
        List<Assignee> assignees = schedule.getAssignee(); // 원래 여기서 지연 로딩 걸어서 이 부분에서 쿼리가 실행되면서
        // 가지고 와야하는데 쿼리문이 안 날아가고 있어서, 이유를 찾아야 함

        System.out.println("후");


        // AssigneeDto 리스트를 생성합니다
        List<AssigneeDto> assigneeDtos = assignees.stream()
                .map(assignee -> {
                    AssigneeDto dto = new AssigneeDto();
                    dto.setUserId(assignee.getUser().getUserId());
                    dto.setAssigneeId(assignee.getAssigneeId());
                    dto.setUserName(assignee.getUser().getUserName());
                    dto.setUserEmail(assignee.getUser().getEmail());
                    return dto;
                })
                .collect(Collectors.toList());

        resDto.setAssignees(assigneeDtos);
        return resDto;
    }

    // 일정 수정
    @Transactional
    public ScheduleUpdateDtoResponse updateSchedule(int id, Schedule updatedSchedule) {
        // 요청받은 스케쥴id, 유저id 존재 확인
        // assignee 추가해서 잠시 주석 처리
//        if (scheduleRepository.existsById(id) && userRepository.existsById(updatedSchedule.getUser().getUserId())) {
        if (scheduleRepository.existsById(updatedSchedule.getScheduleId()) ) {
            ScheduleUpdateDtoResponse resDto = new ScheduleUpdateDtoResponse(scheduleRepository.save(updatedSchedule));

            resDto.setAssignees(assigneeRepository.findBySchedule_ScheduleId(updatedSchedule.getScheduleId()));
            return resDto;
        }
        return null; // 또는 예외를 던질 수 있습니다.
    }

    // 페이징
    // 스케쥴 제목, 내용, 작성일 수정일, 작성 유저명, 댓글 수 조회
    public Page<SchedulePagingResponseDto> getSchedules(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Schedule> pageSchedule = scheduleRepository.findAll(pageable);

        // Schedule을 SchedulePagingResponseDto로 변환
        Page<SchedulePagingResponseDto> dtoPage = pageSchedule.map(SchedulePagingResponseDto::new);

        return dtoPage;



    }

    // 일정 삭제
    @Transactional
    public void deleteSchedule(int id){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 스케줄이 존재하지 않습니다. ID: " + id));

        scheduleRepository.delete(schedule);
    }

    public List<ScheduleGetAllDtoResponse> getAllSchedule(){

        List<Schedule> scheduleList = scheduleRepository.findAll();

        // List<schedule> -> List<Dto>
        List<ScheduleGetAllDtoResponse> scheduleDtoList = scheduleList.stream()
                .map(schedule -> {
                    ScheduleGetAllDtoResponse dto = new ScheduleGetAllDtoResponse();
                    dto.setScheduleId(schedule.getScheduleId());
                    dto.setModDate(schedule.getModDate());
                    dto.setContent(schedule.getContent());
                    dto.setRegDate(schedule.getRegDate());
                    dto.setTitle(schedule.getTitle());
                    return dto;
                })
                .collect(Collectors.toList());


        return scheduleDtoList;
    }
}