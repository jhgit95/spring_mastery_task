package com.sparta.spring_mastery_task.controller;


import com.sparta.spring_mastery_task.config.PasswordEncoder;
import com.sparta.spring_mastery_task.dto.loginDto.LoginDtoRequest;
import com.sparta.spring_mastery_task.dto.userSaveDto.UserSaveDtoResponse;
import com.sparta.spring_mastery_task.entity.User;
import com.sparta.spring_mastery_task.jwt.JwtUtil;
import com.sparta.spring_mastery_task.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원 가입
    // reqDto 필요
    // 이메일 유니크 필요
    @PostMapping("/public/sign-up")
    public ResponseEntity<UserSaveDtoResponse> createUser(@RequestBody User user, HttpServletResponse httpRes) {
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
    // 날짜, 권한, email은 수정될 수 없게 개선 필요
    @PutMapping
    public ResponseEntity<UserSaveDtoResponse> updateUser(@RequestBody User updatedUser) {
        System.out.println("updateUser.user = " + updatedUser.getUserId());
        User user = userService.updateUser(updatedUser);
//        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
        System.out.println("user = " + user.getUserId());
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
    @GetMapping("/public/login")
    public ResponseEntity<String> login(@RequestBody LoginDtoRequest reqDto, HttpServletResponse httpRes) {

        boolean checkLogin = userService.login(reqDto, httpRes);
        System.out.println("checkLogin = " + checkLogin);

        if (checkLogin) {
            return ResponseEntity.ok("로그인 완료");
        } else {
            return ResponseEntity.status(400).body("에러 발생 : 로그인");
        }


    }

    // 암호화 테스트
    @GetMapping("/public/test/{id}")
    public String testCrypt(@PathVariable String id) {
        System.out.println("id = " + id);
        System.out.println(passwordEncoder.encode(id));

        System.out.println(passwordEncoder.encode("1"));
        System.out.println(passwordEncoder.encode("1"));
        System.out.println(passwordEncoder.encode("1"));
        System.out.println(passwordEncoder.encode("1"));


        System.out.println("평문 + 암호문 맞잖아 " + passwordEncoder.matches(id, "$2a$04$HW5GTjJeN0SD44jYEiG3T..RXBR5AjYK4a22n4puzj9xGl9c70NjO"));
        return passwordEncoder.encode("zz");

    }
}