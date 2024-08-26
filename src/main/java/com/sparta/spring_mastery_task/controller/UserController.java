package com.sparta.spring_mastery_task.controller;


import com.sparta.spring_mastery_task.dto.userSaveDto.UserSaveDtoResponse;
import com.sparta.spring_mastery_task.entity.User;
import com.sparta.spring_mastery_task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // 유저 저장
    // resDto 필요
    @PostMapping
    public ResponseEntity<UserSaveDtoResponse> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        UserSaveDtoResponse resDto = new UserSaveDtoResponse(savedUser);
        return ResponseEntity.ok(resDto);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserSaveDtoResponse> getUser(@PathVariable int id) {
//        Optional<User> user = userService.getUserById(id);
        User user = userService.getUserById(id).orElse(null);
        UserSaveDtoResponse resDto = new UserSaveDtoResponse(user);
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.ok(resDto);
    }

    // 유저 수정
    @PutMapping
    public ResponseEntity<UserSaveDtoResponse> updateUser( @RequestBody User updatedUser) {
        User user = userService.updateUser( updatedUser);
//        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
        UserSaveDtoResponse resDto = new UserSaveDtoResponse(user);
        return ResponseEntity.ok(resDto);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (userService.getUserById(id).isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}