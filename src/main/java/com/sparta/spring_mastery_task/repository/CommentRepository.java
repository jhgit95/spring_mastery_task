package com.sparta.spring_mastery_task.repository;


import com.sparta.spring_mastery_task.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    void deleteByUser_UserId(int userId);
}
