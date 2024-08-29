package com.sparta.spring_mastery_task.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "user")  // 테이블 이름 명시
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")  // 컬럼 이름 명시
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "reg_date")
    private String regDate;

    @Column(name = "mod_date")
    private String modDate;

    @Column(name="auth")
    private String auth;

    @Column(name="pw")
    private String pw;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignee> assignee = new ArrayList<>();
}