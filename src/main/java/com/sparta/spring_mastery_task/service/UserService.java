package com.sparta.spring_mastery_task.service;

import com.sparta.spring_mastery_task.entity.User;
import com.sparta.spring_mastery_task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 유저 저장
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 단건 조회
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    // 유저 수정
    public User updateUser(int id, User updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setUserId(id);
            return userRepository.save(updatedUser);
        }
        return null; // 또는 예외를 던질 수 있습니다.
    }

    // 유저 삭제
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}