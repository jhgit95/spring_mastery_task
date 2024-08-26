package com.sparta.spring_mastery_task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "assignee")
public class Assignee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignee_id")
    int assigneeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // 외래키 컬럼 설정
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @Column(name = "writer_id")
    private int writerId;

}
