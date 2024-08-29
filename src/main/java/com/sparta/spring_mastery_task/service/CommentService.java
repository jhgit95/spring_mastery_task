package com.sparta.spring_mastery_task.service;

import com.sparta.spring_mastery_task.dto.CommentRequestDto;
import com.sparta.spring_mastery_task.entity.Comment;
import com.sparta.spring_mastery_task.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    // 댓글 저장
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }


    // 댓글 조회 id
    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null);

    }

    // 댓글 전체 조회
    public List<Comment> getCommentAll() {
        return commentRepository.findAll();

    }

    // 댓글 수정
    // 검증, 예외 처리 필요
    @Transactional
    public Comment updateComment(Comment updateComment) {
        Comment existingComment = commentRepository.findById(updateComment.getCommentId()).orElse(null);
        existingComment.setContent(updateComment.getContent());
        existingComment.setModDate("now : 나중에 바꿔야함");
        return commentRepository.save(existingComment);

    }

    // 댓글 삭제
    // 검증, 예외 처리 필요
    @Transactional
    public void deleteComment(CommentRequestDto reqDto) {
        commentRepository.deleteById(reqDto.getCommentId());
    }


}
