package com.huuphuoc.api.redis.service;

import com.huuphuoc.api.redis.model.TokenBlacklist;
import com.huuphuoc.api.redis.repository.RedisRepository;
import com.huuphuoc.api.security.JWTGennerator;
import com.huuphuoc.api.security.utils.JWTinfor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Builder
@AllArgsConstructor
public class RedisService {

    private   RedisRepository redisRepository;

    private final JWTGennerator jwtGennerator;
//    private   TokenBlacklist tokenBlacklist;


    public Object  LogoutService(String token){

        JWTinfor jwTinfor =  jwtGennerator.pareToken(token);
        try {
            String jwtID = jwTinfor.getJwtID();
            Date issureTime = jwTinfor.getIssuedAt();
            Date expiredTime = jwTinfor.getExpireTime();
            if (expiredTime.before(new Date())){
            return null;
            }
            TokenBlacklist tokenBlacklist = TokenBlacklist.builder().jwtID(jwtID.toString()).expiredTime(expiredTime.getTime() - issureTime.getTime()).build();
            redisRepository.save(tokenBlacklist);
            return "Log out thành công";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
