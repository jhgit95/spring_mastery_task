package com.sparta.spring_mastery_task.controller;

import com.sparta.spring_mastery_task.dto.likesLikesFeedDto.LikeLikesFeedDtoRequest;
import com.sparta.spring_mastery_task.dto.likesLikesFeedDto.LikeLikesFeedDtoResponse;
import com.sparta.spring_mastery_task.entity.Likes;
import com.sparta.spring_mastery_task.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    @PostMapping
    public String likesFeed(@RequestBody LikeLikesFeedDtoRequest reqDto){
        likesService.likesFeed(reqDto);
        return "좋아요 완료";

    }
    @GetMapping("/{scheduleId}")
    public List<LikeLikesFeedDtoResponse> getSchedulesLikes(@PathVariable int scheduleId){
        return likesService.getSchedulesLikes(scheduleId);
    }
}
