package com.sparta.spring_mastery_task.service;


import com.sparta.spring_mastery_task.entity.Schedule;
import com.sparta.spring_mastery_task.repository.ScheduleRepository;
import com.sparta.spring_mastery_task.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Slf4j
@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;

    // 일정 저장
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // 단건 조회
    public Schedule getScheduleById(int id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    // 일정 수정
    public Schedule updateSchedule(int id, Schedule updatedSchedule) {
        // 요청받은 스케쥴id, 유저id 존재 확인
        if (scheduleRepository.existsById(id)&&userRepository.existsById(updatedSchedule.getUser().getUserId())) {
            updatedSchedule.setScheduleId(id);
            return scheduleRepository.save(updatedSchedule);
        }
        return null; // 또는 예외를 던질 수 있습니다.
    }
}