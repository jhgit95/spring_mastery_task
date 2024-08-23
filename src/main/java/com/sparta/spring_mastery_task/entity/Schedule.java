package com.sparta.spring_mastery_task.entity;

import com.sparta.spring_mastery_task.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "schedule")  // 테이블 이름 명시
@NoArgsConstructor
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

    @Column(name = "content")
    private String content;

    @Column(name = "reg_date")
    private String regDate;

    @Column(name = "mod_date")
    private String modDate;

    public Schedule(ScheduleRequestDto reqDto) {
        this.user = reqDto.getUser();
        this.scheduleId = reqDto.getScheduleId();
        this.title = reqDto.getTitle();
        this.content = reqDto.getContent();
        this.regDate = reqDto.getRegDate();
        this.modDate = reqDto.getModDate();

    }


}