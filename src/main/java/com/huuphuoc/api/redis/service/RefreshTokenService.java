package com.huuphuoc.api.redis.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;


@Service
@AllArgsConstructor

public class RefreshTokenService {
    private  final StringRedisTemplate redisTemplate;
    private final long REFRESH_TOKEN_EXPIRY = 7 * 24 * 60 * 60;
    public String createRefreshToken(Authentication authentication){
        String key = "RT" + authentication.getName();
        String refeshToken =  UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(key,refeshToken, Duration.ofSeconds(REFRESH_TOKEN_EXPIRY));
        String refreshTokentemp = redisTemplate.opsForValue().get(key);

        return refreshTokentemp;
    }





    public boolean validateRefreshToken( String email , String tokenReceived) {
        String key = "RT:" + email;
        String storedToken = redisTemplate.opsForValue().get(key);
        return storedToken != null && storedToken.equals(tokenReceived);
    }




}
