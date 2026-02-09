package com.huuphuoc.api.security.utils;

import lombok.*;


import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTinfor {

    String jwtID;
    Date issuedAt;
    Date expireTime;


}
