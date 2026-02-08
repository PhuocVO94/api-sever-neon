package com.huuphuoc.api.security;


import lombok.Data;

@Data
public class JWTAuthDTO {
    private  String accessToken;
    private  String TokenType;

    public JWTAuthDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
