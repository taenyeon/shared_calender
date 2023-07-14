package com.project.shared_calender.common.security;

import com.project.shared_calender.common.security.constant.TokenStatus;
import com.project.shared_calender.common.security.domain.TokenDto;
import com.project.shared_calender.domain.user.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static final String JWT_SECRET = "secretKey";
    public static final String REFRESH_TOKEN_KEY = "refreshToken:";

    private final long ACCESS_TOKEN_EXPIRATION_MS = Duration.ofMinutes(10).toMillis();
    private final long REFRESH_TOKEN_EXPIRATION_MS = Duration.ofDays(2).toMillis();

    private final RedisTemplate<String, Object> redisTemplate;

    public TokenDto generateToken(UserEntity user) {
        Date now = new Date();
        String accessToken = generateAccessToken(user.getId(), now);
        String refreshToken = generateRefreshToken(user.getId(), now);
        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String generateAccessToken(long userSeq, Date now) {
        Date expiredDate = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION_MS);
        return Jwts.builder()
                .setSubject(String.valueOf(userSeq))
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String generateRefreshToken(long userSeq, Date now) {
        Date expiredDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_MS);
        String refreshToken = Jwts.builder()
                .setSubject(String.valueOf(userSeq))
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        String key = REFRESH_TOKEN_KEY + userSeq;
        redisTemplate.opsForValue().set(key, refreshToken);
        redisTemplate.expire(key, REFRESH_TOKEN_EXPIRATION_MS, TimeUnit.MILLISECONDS);
        return refreshToken;
    }

    public void dropRefreshToken(String refreshToken) {
        String key = REFRESH_TOKEN_KEY + refreshToken;
        redisTemplate.delete(key);
    }

    public static String getUserSeqFromJWT(String token) {
        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();

        } catch (Exception e) {
            return null;
        }
    }

    public static TokenStatus validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return TokenStatus.ALLOW;
        } catch (ExpiredJwtException e) {
            return TokenStatus.EXPIRED;
        } catch (Exception e) {
            return TokenStatus.NOT_ALLOW;
        }
    }

    public static UserEntity getUserEntity() {
        return (UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getDetails();
    }

    public void setUser(long userSeq){
//        UserEntity userEntity = userCacheService.getCacheUserDetail(userSeq);
//        UserAuthentication userAuthentication = new UserAuthentication(userEntity, null);
//        userAuthentication.setDetails(userEntity);
//        SecurityContextHolder.getContext().setAuthentication(userAuthentication);

    }
}
