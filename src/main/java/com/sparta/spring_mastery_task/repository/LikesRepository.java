package com.sparta.spring_mastery_task.repository;

import com.sparta.spring_mastery_task.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Integer> {

    List<Likes> findBySchedule_ScheduleId(int id);
}
