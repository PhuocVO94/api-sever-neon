package com.huuphuoc.api.security;


import lombok.Data;

@Data
public class JWTAuthDTO {
    private  String accessToken;
    private  String resfeshToken;

    public JWTAuthDTO(String accessToken, String resfeshToken) {
        this.accessToken = accessToken;
        this.resfeshToken  = resfeshToken;
    }
}
