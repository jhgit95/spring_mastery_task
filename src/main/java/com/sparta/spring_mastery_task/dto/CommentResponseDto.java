package com.sparta.spring_mastery_task.dto;

import com.sparta.spring_mastery_task.entity.Comment;
import com.sparta.spring_mastery_task.entity.Schedule;
import com.sparta.spring_mastery_task.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {

    int commentId;
    String content;
    String regDate;
    String modDate;
    int userId;
    int scheduleId;

    public CommentResponseDto(Comment comment){
        this.commentId=comment.getCommentId();
        this.content=comment.getContent();
        this.regDate=comment.getRegDate();
        this.modDate=comment.getModDate();
        this.userId=comment.getUser().getUserId();
        this.scheduleId=comment.getSchedule().getScheduleId();
    }

}
