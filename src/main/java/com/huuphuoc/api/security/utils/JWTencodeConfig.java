package com.huuphuoc.api.security.utils;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;


@Component
public class JWTencodeConfig implements JwtDecoder {
    @Override
    public Jwt decode(String token) throws JwtException {
        SecretKeySpec secretKey = new SecretKeySpec(
                SecurityConstants.JWT_Secret.getBytes(),
                "HmacSHA256"
        );

        // 2. Sử dụng NimbusJwtDecoder với phương thức withSecretKey
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build().decode(token);
    }
}
