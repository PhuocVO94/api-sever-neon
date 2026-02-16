package com.huuphuoc.api.security;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.huuphuoc.api.common.enums.Roles;
import com.huuphuoc.api.redis.model.TokenBlacklist;
import com.huuphuoc.api.redis.repository.RedisRepository;
import com.huuphuoc.api.security.utils.JWTinfor;
import com.huuphuoc.api.security.utils.SecurityConstants;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.JwtSigner;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Component;


import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class JWTGenerator {


//    Hàm tạo token

    public  String Gennerate(String email){
        String username = email;
        Date currentDate = new Date();
        Date expriDate = new Date(currentDate.getTime() + SecurityConstants.JWT_Expiration);
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new  JWTClaimsSet.Builder()
                .subject(username)
                .issueTime(currentDate)
                .expirationTime(expriDate)
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SecurityConstants.JWT_Secret.getBytes()));
            return  jwsObject.serialize();

        } catch (JOSEException e) {
            throw new RuntimeException("Lỗi khi ký token: " + e.getMessage());
        }


    }

//    Hàm giải mã token

    public String getUsernameFromJWT(String token) {
        try {

            SignedJWT signedJWT = SignedJWT.parse(token);

            MACVerifier verifier = new MACVerifier(SecurityConstants.JWT_Secret.getBytes());


            if (signedJWT.verify(verifier)) {
                return signedJWT.getJWTClaimsSet().getSubject();
            } else {
                throw new RuntimeException("Chữ ký JWT không hợp lệ!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Không thể trích xuất username: " + e.getMessage());
        }
    }
    // Sửa tại file JWTGenerator.java
    public boolean validateToken(String token) {
        try {
            // Sử dụng thư viện Nimbus để verify đồng bộ với hàm Generate
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SecurityConstants.JWT_Secret.getBytes());

            // Kiểm tra chữ ký và thời gian hết hạn
            if (!signedJWT.verify(verifier)) {
                return false;
            }

            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
            return expiration.after(new Date());

        } catch (Exception e) {
            // Trả về false thay vì throw Exception để tránh lỗi 500
            return false;
        }
    }

    public JWTinfor pareToken(String token) throws ParseException {

        SignedJWT signedJWT = SignedJWT.parse(token);
        return JWTinfor.builder()
                .jwtID(signedJWT.getJWTClaimsSet().getJWTID())
                .issuedAt(signedJWT.getJWTClaimsSet().getIssueTime())
                .expireTime(signedJWT.getJWTClaimsSet().getExpirationTime())
                .build();

    }
}
