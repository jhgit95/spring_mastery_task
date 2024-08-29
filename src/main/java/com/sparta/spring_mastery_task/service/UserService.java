package com.sparta.spring_mastery_task.service;

import com.sparta.spring_mastery_task.config.PasswordEncoder;
import com.sparta.spring_mastery_task.dto.loginDto.LoginDtoRequest;
import com.sparta.spring_mastery_task.entity.User;
import com.sparta.spring_mastery_task.exception.EmailPwException;
import com.sparta.spring_mastery_task.jwt.JwtUtil;
import com.sparta.spring_mastery_task.repository.CommentRepository;
import com.sparta.spring_mastery_task.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final  CommentRepository commentRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    // 유저 저장
    public User saveUser(User user, HttpServletResponse httpRes) {
        String encodePw = passwordEncoder.encode(user.getPw());
        user.setPw(encodePw);
        String test = jwtUtil.createToken(user.getUserName(),user);
        System.out.println("test = " + test);
        jwtUtil.addJwtToCookie(test, httpRes);
        return userRepository.save(user);
    }

    // 단건 조회
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    // 유저 수정
    public User updateUser( User updatedUser) {
        if (userRepository.existsById(updatedUser.getUserId())) {
//            updatedUser.setUserId(updatedUser.getUserId());
            return userRepository.save(updatedUser);
        }
        return null; // 또는 예외를 던질 수 있습니다.
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(int id) {
        commentRepository.deleteByUser_UserId(id);
        userRepository.deleteById(id);
    }

    // 로그인
    @Transactional
    public boolean login(LoginDtoRequest reqDto, HttpServletResponse httpRes) {
        // 요청 이메일 정보 불러오기
        User reqUser = userRepository.findByEmail(reqDto.getEmail());

        // 레포에 있는 비밀번호와 요청에 들어온 비밀번호 확인
        if(passwordEncoder.matches(reqDto.getPw(),reqUser.getPw())){
            String token = jwtUtil.createToken(reqDto.getEmail(),reqUser);
            jwtUtil.addJwtToCookie(token, httpRes);
            return true;
        }else{
            throw new EmailPwException("일치하지 않는 비밀번호");
        }
    }
}