package com.sparta.spring_mastery_task.controller;

import com.sparta.spring_mastery_task.dto.CommentRequestDto;
import com.sparta.spring_mastery_task.dto.CommentResponseDto;
import com.sparta.spring_mastery_task.entity.Comment;
import com.sparta.spring_mastery_task.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
