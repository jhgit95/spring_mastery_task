package com.sparta.spring_mastery_task.entity;

import com.sparta.spring_mastery_task.dto.CommentRequestDto;
import com.sparta.spring_mastery_task.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")  // 테이블 이름 명시
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @Column(name="content")
    private String content;

    @Column(name="reg_date")
    private String regDate;

    @Column(name = "mod_date")
    String modDate;

    @ManyToOne
    @JoinColumn(name="schedule_id",nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Comment(CommentRequestDto reqDto){
        this.user=reqDto.getUser();
        this.schedule=reqDto.getSchedule();
        this.commentId =reqDto.getCommentId();
        this.content=reqDto.getContent();
        this.regDate=reqDto.getRegDate();
        this.modDate=reqDto.getModDate();
    }
}
