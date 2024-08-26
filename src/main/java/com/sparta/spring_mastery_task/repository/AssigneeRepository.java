package com.sparta.spring_mastery_task.repository;

import com.sparta.spring_mastery_task.entity.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssigneeRepository extends JpaRepository<Assignee,Integer> {
    List<Assignee> findBySchedule_ScheduleId(Integer scheduleId);
}
