package com.huuphuoc.api.security;

import com.huuphuoc.api.redis.model.TokenBlacklist;
import com.huuphuoc.api.redis.repository.RedisRepository;
import com.huuphuoc.api.security.utils.JWTinfor;
import com.huuphuoc.api.security.utils.SecurityConstants;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class JWTGennerator {

    private  final RedisRepository redisRepository;


    public  String Gennerate(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expriDate = new Date(currentDate.getTime() + SecurityConstants.JWT_Expiration);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expriDate)
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,SecurityConstants.JWT_Secret)
                .compact();
        return token;

    }



    public  String RefreshGennerate(Authentication authentication){

        String username = authentication.getName();
        Date currentDate = new Date();
        Date expriDate = Date.from(currentDate.toInstant().plus(10, ChronoUnit.DAYS ));

        return Jwts.builder().setId(UUID.randomUUID().toString())
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expriDate)
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.JWT_Secret)
                .compact();

    }



    public  String getUsernameFromJWT (String token){

        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_Secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public  boolean validateToken(String token){

        try {
          Claims claims =  Jwts.parser().setSigningKey(SecurityConstants.JWT_Secret).parseClaimsJws(token).getBody();

            Optional<TokenBlacklist> tokenBlacklist = redisRepository.findById(claims.getId());
            if (tokenBlacklist.isPresent()){
                throw new RuntimeException("Token invalid");
            }

            return true;
        } catch (Exception e) {
            throw new AuthenticationException("JWT was expired of incorrect ") {
            };
        }
    }

    public JWTinfor pareToken(String token){


        Claims claimstoken = Jwts.parser().setSigningKey(SecurityConstants.JWT_Secret).parseClaimsJws(token).getBody();

        return JWTinfor.builder()
                .jwtID(claimstoken.getId())
                .issuedAt(claimstoken.getIssuedAt())
                .expireTime(claimstoken.getExpiration())
                .build();
    }
}
