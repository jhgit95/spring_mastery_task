package com.sparta.spring_mastery_task.controller;

import com.sparta.spring_mastery_task.dto.CommentRequestDto;
import com.sparta.spring_mastery_task.dto.CommentResponseDto;
import com.sparta.spring_mastery_task.entity.Comment;
import com.sparta.spring_mastery_task.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<CommentResponseDto> creatComment(@RequestBody CommentRequestDto reqDto) {
        Comment comment = new Comment(reqDto);
        CommentResponseDto resDto = new CommentResponseDto(commentService.saveComment(comment));
        return ResponseEntity.ok(resDto);
    }

    // 댓글 id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable int id) {
        CommentResponseDto resDto = new CommentResponseDto(commentService.getCommentById(id));
        return ResponseEntity.ok(resDto);
    }

    // 전체 댓글 조회
    @GetMapping("/all")
    public ResponseEntity<List<CommentResponseDto>> getAllComment() {
        // 서비스에서 댓글 목록을 가져옵니다.
        List<Comment> comments = commentService.getCommentAll();

        // 댓글 목록을 DTO 목록으로 변환합니다.
        List<CommentResponseDto> resDto = comments.stream()
                .map(comment -> new CommentResponseDto(comment))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resDto);
    }

    // 댓글 수정
    // 댓글 내용만 수정 가능
    // 수정일은 메서드 사용한 시간으로 서버에서 넣어주고
    // 등록일은 원래 등록일을 유지시켜야 함
    @PutMapping
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody CommentRequestDto reqDto) {
        Comment comment = new Comment(reqDto);
        CommentResponseDto resDto = new CommentResponseDto(commentService.updateComment(comment));
        return ResponseEntity.ok(resDto);

    }


    // 댓글 삭제
    @DeleteMapping
    public ResponseEntity<String> deleteComment(@RequestBody CommentRequestDto reqDto) {
        commentService.deleteComment(reqDto);
        return ResponseEntity.ok("삭제 완료");

    }

}
