package com.sparta.spring_mastery_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 자동으로 시간 넣어주는 어노테이션?
@EnableJpaAuditing
@SpringBootApplication
public class SpringMasteryTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMasteryTaskApplication.class, args);
    }

}
