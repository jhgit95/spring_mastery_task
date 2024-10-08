package com.sparta.spring_mastery_task.filter;


import com.sparta.spring_mastery_task.entity.User;
import com.sparta.spring_mastery_task.exception.ForbiddenException;
import com.sparta.spring_mastery_task.exception.TokenExpireException;
import com.sparta.spring_mastery_task.exception.TokenMissingException;
import com.sparta.spring_mastery_task.jwt.JwtUtil;
import com.sparta.spring_mastery_task.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@Component
@Order(2)
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("필터 되냐");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI();

        // 여기 넣는 url은 토큰 검증을 하지 않음
        if (StringUtils.hasText(url) &&
//                (url.startsWith("/schedules22") || url.startsWith("/users") || url.startsWith("/comment"))
                (url.startsWith("/users/public")||url.startsWith("/likes")||url.startsWith("/likes/"))
        ) {
            System.out.println("첫 if문");
            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행
            chain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            System.out.println("첫 if문의 else");
            // 나머지 API 요청은 인증 처리 진행
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);


            // admin 권한이 필요한 요청의 경우
            if (StringUtils.hasText(url) && url.startsWith("/schedules/auth")) {
                System.out.println("필터 완료~~~~완료~~~~~");

                String authCheckToken = jwtUtil.getTokenFromRequest(httpServletRequest);
                if (!jwtUtil.getAuthFromToken(authCheckToken).equals("admin")) {
                    System.out.println("토큰 검증 jwtUtil.validateToken(token) 실패 ");
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "권한 없음");
                    return;
                }
            }


            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);
                try {

                    // 토큰 검증
                    if (jwtUtil.validateToken(token)) {
                        System.out.println("토큰 검증 jwtUtil.validateToken(token) 완료 ");
                        chain.doFilter(request, response); // 다음 Filter 로 이동.
                    } else {
                        System.out.println("토큰 검증 jwtUtil.validateToken(token) 실패 ");
                        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 JWT");
                        return;
                    }
                } catch (Exception e) {

                    log.error("예외 발생: ", e);
                    System.out.println("토큰 검증 jwtUtil.validateToken(token) 실패 ");
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 JWT");
                    return;
                }


                // 토큰에서 사용자 정보 가져오기  // 이 부분 볼 것
                Claims info = jwtUtil.getUserInfoFromToken(token);

                User user = userRepository.findByEmail(info.getSubject());
//                        .orElseThrow(() ->
//                        new NullPointerException("Not Found User")
//                );

//                request.setAttribute("user", user); // 이거 없애야 할 수도 있음

            } else {
                System.out.println("토큰이 없어");
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "JWT가 없음");
                return;
            }
        }
    }

}