package com.huuphuoc.api.redis.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;


@Service
@AllArgsConstructor

public class ResfeshTokenService {
    private  final StringRedisTemplate redisTemplate;
    private final long REFRESH_TOKEN_EXPIRY = 7 * 24 * 60 * 60;
    public String createRefeshToken(Authentication authentication){
        String key = "RT" + authentication.getName();
        String refeshToken =  UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(key,refeshToken, Duration.ofSeconds(REFRESH_TOKEN_EXPIRY));
        return refeshToken ;
    }





    public boolean validateRefreshToken( Authentication authentication, String tokenReceived) {
        String key = "RT:" + authentication.getName();
        String storedToken = redisTemplate.opsForValue().get(key);

        // Kiểm tra xem token gửi lên có khớp với token trong Redis không
        return storedToken != null && storedToken.equals(tokenReceived);
    }




}
