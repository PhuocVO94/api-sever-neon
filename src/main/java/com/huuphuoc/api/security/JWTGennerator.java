package com.huuphuoc.api.security;

import com.huuphuoc.api.security.utils.JWTinfor;
import com.huuphuoc.api.security.utils.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGennerator {

    public  String Gennerate(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expriDate = new Date(currentDate.getTime() + SecurityConstants.JWT_Expiration);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expriDate)
                .signWith(SignatureAlgorithm.ES512,SecurityConstants.JWT_Secret)
                .compact();
        return token;

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
            Jwts.parser().setSigningKey(SecurityConstants.JWT_Secret).parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            throw new AuthenticationException("JWT was expired of incorrect ") {
            };
        }
    }

    public JWTinfor pareToken(String token){


        Claims claimstoken = Jwts.parser().parseClaimsJws(token).getBody();

        return JWTinfor.builder()
                .jwtID(claimstoken.getId())
                .issuedAt(claimstoken.getIssuedAt())
                .expireTime(claimstoken.getExpiration())
                .build();
    }
}
