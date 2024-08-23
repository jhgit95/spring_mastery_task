package com.sparta.spring_mastery_task.repository;

import com.sparta.spring_mastery_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}