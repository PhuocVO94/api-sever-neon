package com.huuphuoc.api.redis.service;

import com.huuphuoc.api.redis.model.TokenBlacklist;
import com.huuphuoc.api.redis.repository.RedisRepository;
import com.huuphuoc.api.security.JWTGenerator;
import com.huuphuoc.api.security.utils.JWTinfor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisRepository redisRepository;
    private  final StringRedisTemplate redisTemplate;
    private final JWTGenerator jwtGenerator;






    public Object  LogoutService(String token) throws ParseException {
        JWTinfor jwTinfor =  jwtGenerator.pareToken(token);



        try {
            String jwtID = jwTinfor.getJwtID();
            Long expiredTime = jwTinfor.getExpireTime().getTime();
            String email = jwTinfor.getEmail();
            Long currenTime = System.currentTimeMillis();
            if (expiredTime < currenTime){
            return null;
            }
            TokenBlacklist tokenBlacklist = TokenBlacklist.builder()
                    .jwtID(jwtID)
                    .expiredTime(expiredTime - currenTime)
                    .build();

            redisRepository.save(tokenBlacklist);

            String key = "RT" +email;
            redisTemplate.delete(key);
            return "Log out thành công";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
