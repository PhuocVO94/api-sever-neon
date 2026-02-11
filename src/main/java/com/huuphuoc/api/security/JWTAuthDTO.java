package com.huuphuoc.api.security;


import lombok.Data;

@Data
public class JWTAuthDTO {
    private  String accessToken;
    private  String refeshToken;

    public JWTAuthDTO(String accessToken, String refeshToken) {
        this.accessToken = accessToken;
        this.refeshToken = refeshToken;
    }
}
