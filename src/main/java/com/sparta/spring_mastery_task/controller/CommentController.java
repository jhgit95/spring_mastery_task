package com.sparta.spring_mastery_task.controller;

import com.sparta.spring_mastery_task.dto.CommentRequestDto;
import com.sparta.spring_mastery_task.dto.CommentResponseDto;
import com.sparta.spring_mastery_task.entity.Comment;
import com.sparta.spring_mastery_task.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> creatComment(@RequestBody CommentRequestDto reqDto){
        Comment comment = new Comment(reqDto);

        CommentResponseDto resDto= new CommentResponseDto(commentService.saveComment(comment));
        return ResponseEntity.ok(resDto);
    }

    // 댓글 id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable int id){
        CommentResponseDto resDto= new CommentResponseDto(commentService.getCommentById(id));
        return ResponseEntity.ok(resDto);
    }

    // 전체 댓글 조회
    @GetMapping("/all")
    public ResponseEntity<List<CommentResponseDto>> getAllComment(){
        // 서비스에서 댓글 목록을 가져옵니다.
        List<Comment> comments = commentService.getCommentAll();

        // 댓글 목록을 DTO 목록으로 변환합니다.
        List<CommentResponseDto> resDtos = comments.stream()
                .map(comment -> new CommentResponseDto(comment))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resDtos);


    }

    // 댓글 수정

    // 댓글 삭제

}
