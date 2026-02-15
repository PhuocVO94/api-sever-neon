package com.huuphuoc.api.redis.service;

import com.huuphuoc.api.redis.model.TokenBlacklist;
import com.huuphuoc.api.redis.repository.RedisRepository;
import com.huuphuoc.api.security.JWTGenerator;
import com.huuphuoc.api.security.utils.JWTinfor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisRepository redisRepository;

    private final JWTGenerator jwtGenerator;
    private   TokenBlacklist tokenBlacklist;


    public Object  LogoutService(String token) throws ParseException {

        JWTinfor jwTinfor =  jwtGenerator.pareToken(token);
        System.out.println("Pare JWT correctly" + jwTinfor.getJwtID());
        try {
            String jwtID = jwTinfor.getJwtID();
            Date issureTime = jwTinfor.getIssuedAt();
            Date expiredTime = jwTinfor.getExpireTime();
            if (expiredTime.before(new Date())){
            return null;
            }
            tokenBlacklist = TokenBlacklist.builder().jwtID(jwtID.toString()).expiredTime(expiredTime.getTime() - issureTime.getTime()).build();
            redisRepository.save(tokenBlacklist);
            return "Log out thành công";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
