package com.sparta.spring_mastery_task.service;

import com.sparta.spring_mastery_task.entity.Comment;
import com.sparta.spring_mastery_task.repository.CommentRepository;
import com.sparta.spring_mastery_task.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    // 댓글 저장
    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }


    // 댓글 조회 id
    public Comment getCommentById(int id){
        return commentRepository.findById(id).orElse(null);

    }

    // 댓글 전체 조회
    public List<Comment> getCommentAll(){
        return commentRepository.findAll();

    }

    // 댓글 수정

    // 댓글 삭제


}
