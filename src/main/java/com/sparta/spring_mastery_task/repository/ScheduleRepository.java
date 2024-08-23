package com.sparta.spring_mastery_task.repository;

import com.sparta.spring_mastery_task.dto.SchedulePagingResponseDto;
import com.sparta.spring_mastery_task.dto.ScheduleResponseDto;
import com.sparta.spring_mastery_task.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query(value = "SELECT new com.sparta.spring_mastery_task.dto.SchedulePagingResponseDto(s.scheduleId, s.title, s.content, s.regDate, s.modDate, u.userName,u.userId, COUNT(c)) " +
            "FROM Schedule s " +
            "JOIN s.user u " +
            "LEFT JOIN Comment c ON s.scheduleId = c.schedule.scheduleId " +
            "GROUP BY s.scheduleId, u.userName " +
            "ORDER BY s.modDate DESC")
    Page<SchedulePagingResponseDto> findSchedulesWithDetails(Pageable pageable);

}
