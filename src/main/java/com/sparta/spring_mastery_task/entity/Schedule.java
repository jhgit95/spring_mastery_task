package com.sparta.spring_mastery_task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "schedule")  // 테이블 이름 명시
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")  // 컬럼 이름 명시
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // 외래키 컬럼 설정
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "reg_date")
    private String regDate;

    @Column(name = "mod_date")
    private String modDate;

    @Column(name = "content")
    private String content;
}