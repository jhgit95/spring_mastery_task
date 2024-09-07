package com.sparta.spring_mastery_task.entity;

import com.sparta.spring_mastery_task.dto.likesLikesFeedDto.LikeLikesFeedDtoRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "likes")
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private int likesId;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Likes(LikeLikesFeedDtoRequest reqDto){
        Schedule reqSchedule = new Schedule();
        reqSchedule.setScheduleId(reqDto.getScheduleId());
        this.schedule=reqSchedule;

        User reqUser = new User();
        reqUser.setUserId(reqDto.getUserId());
        this.user=reqUser;

    }

}
