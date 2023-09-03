package com.project.shared_calender.common.security;

import com.project.shared_calender.common.constant.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.project.shared_calender.common.security.JwtTokenProvider.REFRESH_TOKEN_KEY;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider extends OncePerRequestFilter {

    public static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader(ACCESS_TOKEN_COOKIE_NAME);
        String refreshToken = request.getHeader(REFRESH_TOKEN_COOKIE_NAME);
        String userSeq;
        if (StringUtils.isNotBlank(accessToken)) {
            switch (JwtTokenProvider.validateToken(accessToken)) {
                case ALLOW:

                    log.info("[AUTH CHECK] ALLOW - accessToken : {}, refreshToken : {}", accessToken, refreshToken);
                    userSeq = JwtTokenProvider.getUserSeqFromJWT(accessToken);
                    setUserDetail(Long.parseLong(userSeq));
                    break;

                case EXPIRED:
                    log.info("[AUTH CHECK] EXPIRED - accessToken : {}, refreshToken : {}", accessToken, refreshToken);
                    userSeq = JwtTokenProvider.getUserSeqFromJWT(refreshToken);
                    if (redisTemplate.opsForValue().get(REFRESH_TOKEN_KEY + userSeq) != null) {
                        Date now = new Date();
                        String newAccessToken = jwtTokenProvider.generateAccessToken(Long.parseLong(userSeq), now);
                        setUserDetail(Long.parseLong(userSeq));
                        response.setHeader(ACCESS_TOKEN_COOKIE_NAME, newAccessToken);
                    } else {
                        setUnauthorizationResponse(request);
                    }
                    break;

                case NOT_ALLOW:
                    log.info("[AUTH CHECK] NOT ALLOW - accessToken : {}, refreshToken : {}", accessToken, refreshToken);
                    request.setAttribute("unauthorization", ResponseCode.INVALID_TOKEN.getResultMessage());
                    break;
            }
        } else {
            if (refreshToken != null) {
                log.info("[AUTH CHECK] EXPIRED - accessToken : {}, refreshToken : {}", accessToken, refreshToken);
                userSeq = JwtTokenProvider.getUserSeqFromJWT(refreshToken);
                if (redisTemplate.opsForValue().get(REFRESH_TOKEN_KEY + userSeq) != null) {
                    Date now = new Date();
                    String newAccessToken = jwtTokenProvider.generateAccessToken(Long.parseLong(userSeq), now);
                    setUserDetail(Long.parseLong(userSeq));
                    response.setHeader(ACCESS_TOKEN_COOKIE_NAME, newAccessToken);
                } else {
                    setUnauthorizationResponse(request);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setUnauthorizationResponse(HttpServletRequest request) {
        request.setAttribute("unauthorization", ResponseCode.NOT_ALLOW_REFRESH_TOKEN.getResultMessage());
    }

    private void setUserDetail(long userSeq) {
        jwtTokenProvider.setUser(userSeq);
    }

}
