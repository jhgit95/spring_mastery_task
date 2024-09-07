package com.sparta.spring_mastery_task.service;

import com.sparta.spring_mastery_task.dto.likesLikesFeedDto.LikeLikesFeedDtoRequest;
import com.sparta.spring_mastery_task.dto.likesLikesFeedDto.LikeLikesFeedDtoResponse;
import com.sparta.spring_mastery_task.entity.Likes;
import com.sparta.spring_mastery_task.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;

    public void likesFeed(LikeLikesFeedDtoRequest reqDto){
        Likes likes = new Likes(reqDto);
        likesRepository.save(likes);
    }
    public List<LikeLikesFeedDtoResponse> getSchedulesLikes(int scheduleId){
        List<Likes> likesList =  likesRepository.findBySchedule_ScheduleId(scheduleId);
        return likesList.stream().map(LikeLikesFeedDtoResponse::new).toList();
    }
}
