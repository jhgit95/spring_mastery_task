package com.sparta.spring_mastery_task.dto.likesLikesFeedDto;

import com.sparta.spring_mastery_task.entity.Likes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeLikesFeedDtoResponse {
    int likesId;
    int scheduleId;
    int userId;

    public LikeLikesFeedDtoResponse(Likes likes){
        this.likesId=likes.getLikesId();
        this.scheduleId=likes.getSchedule().getScheduleId();
        this.userId=likes.getUser().getUserId();
    }
}
