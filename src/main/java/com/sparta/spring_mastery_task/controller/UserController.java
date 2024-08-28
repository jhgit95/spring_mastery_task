package com.sparta.spring_mastery_task.controller;


import com.sparta.spring_mastery_task.config.PasswordEncoder;
import com.sparta.spring_mastery_task.dto.loginDto.LoginDtoRequest;
import com.sparta.spring_mastery_task.dto.userSaveDto.UserSaveDtoResponse;
import com.sparta.spring_mastery_task.entity.User;
import com.sparta.spring_mastery_task.jwt.JwtUtil;
import com.sparta.spring_mastery_task.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    // 회원 가입
    // resDto 필요
    @PostMapping("/zz/sign-up")
    public ResponseEntity<UserSaveDtoResponse> createUser(@RequestBody User user,  HttpServletResponse httpRes) {
        User savedUser = userService.saveUser(user, httpRes);
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
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        if (userService.getUserById(id).isPresent()) {
            userService.deleteUser(id);
//            return ResponseEntity.noContent().build();
            return ResponseEntity.ok("유저 삭제 완료");
        }
        return ResponseEntity.notFound().build();
    }

    // 로그인
    @GetMapping("/zz/login")
    public ResponseEntity<String> login(@RequestBody LoginDtoRequest reqDto, HttpServletResponse httpRes){

        boolean checkLogin = userService.login(reqDto, httpRes);
        System.out.println("checkLogin = " + checkLogin);

        if(checkLogin){
            return ResponseEntity.ok("로그인 완료");
        }
        else {
            return ResponseEntity.status(400).body("에러 발생 : 로그인");
        }


    }
}